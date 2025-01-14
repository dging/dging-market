package com.dging.dgingmarket.pay.controller;

import com.dging.dgingmarket.domain.user.*;
import com.dging.dgingmarket.domain.store.*;
import com.dging.dgingmarket.domain.product.*;
import com.dging.dgingmarket.pay.dto.*;
import com.dging.dgingmarket.pay.entity.TossPayment;
import com.dging.dgingmarket.pay.repository.TossPaymentRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/api/payment")
public class PaymentApiController {

    @Value("${payment.api.secret-key}")
    private String apiSecretKey;

    private final TossPaymentRepository tossPaymentRepository;
    private final UserRepository userRepository;      // 추가
    private final StoreRepository storeRepository;    // 추가
    private final ProductRepository productRepository; // 추가

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public PaymentApiController(TossPaymentRepository tossPaymentRepository,
                                UserRepository userRepository,
                                StoreRepository storeRepository,
                                ProductRepository productRepository) {
        this.tossPaymentRepository = tossPaymentRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.productRepository = productRepository;
    }

    /**
     결제 금액 세션에 임시 저장
     **/
    @PostMapping("/saveAmount")
    public ResponseEntity<?> tempSave(HttpSession session, @RequestBody SaveAmountRequest saveAmountRequest) {
        session.setAttribute(saveAmountRequest.getOrderId(), saveAmountRequest.getAmount());
        return ResponseEntity.ok("Payment temp save successful");
    }

    /**
     결제 금액 검증
     **/

    @PostMapping("/verifyAmount")
    public ResponseEntity<?> verifyAmount(HttpSession session, @RequestBody SaveAmountRequest saveAmountRequest) {
        String storedAmount = (String) session.getAttribute(saveAmountRequest.getOrderId());
        if (storedAmount == null || !storedAmount.equals(saveAmountRequest.getAmount())) {
            return ResponseEntity.badRequest().body(PaymentErrorResponse.builder()
                    .code(400)
                    .message("결제 금액 정보가 유효하지 않습니다.")
                    .build());
        }
        session.removeAttribute(saveAmountRequest.getOrderId());
        return ResponseEntity.ok("Payment is valid");
    }


    /**
     클라이언트에서 전달된 주문 상세 정보 저장
     **/
    @PostMapping("/saveOrderDetails")
    public ResponseEntity<OrderDetailsResponse> saveOrderDetails(@RequestBody OrderDetailsRequest orderDetailsRequest, HttpSession session) {
        session.setAttribute("buyerId", orderDetailsRequest.getBuyerId());
        session.setAttribute("storeId", orderDetailsRequest.getStoreId());
        session.setAttribute("productId", orderDetailsRequest.getProductId());

        OrderDetailsResponse response = OrderDetailsResponse.builder()
                .message("Order details saved in session.")
                .build();
        return ResponseEntity.ok(response);
    }


    // 토스에 결제 요청
    @PostMapping("/confirm")
    public ResponseEntity<JSONObject> confirmPayment(HttpServletRequest request, @RequestBody String jsonBody) throws Exception {
        HttpSession session = request.getSession();

        // 세션에서 buyerId, storeId, productId 가져오기
        Long buyerId = (Long) session.getAttribute("buyerId");
        Long storeId = (Long) session.getAttribute("storeId");
        Long productId = (Long) session.getAttribute("productId");

        // 상품에 대한 결제가 이미 이루어졌는지 확인
        boolean isPaymentExist = tossPaymentRepository.existsByProductIdAndTossPaymentStatus(productId, "DONE");

        if (isPaymentExist) {
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "This product has already been paid for.");
            return ResponseEntity.status(400).body(errorResponse);
        }

        JSONObject response = sendRequest(parseRequestData(jsonBody), apiSecretKey, "https://api.tosspayments.com/v1/payments/confirm");

        if (!response.containsKey("error")) {
            saveTossPayment(response, buyerId, storeId, productId);
        }

        int statusCode = response.containsKey("error") ? 400 : 200;
        return ResponseEntity.status(statusCode).body(response);
    }

    private JSONObject parseRequestData(String jsonBody) {
        try {
            return (JSONObject) new JSONParser().parse(jsonBody);
        } catch (ParseException e) {
            logger.error("JSON Parsing Error", e);
            return new JSONObject();
        }
    }

    private JSONObject sendRequest(JSONObject requestData, String secretKey, String urlString) throws IOException {
        HttpURLConnection connection = createConnection(secretKey, urlString);
        try (OutputStream os = connection.getOutputStream()) {
            os.write(requestData.toString().getBytes(StandardCharsets.UTF_8));
        }

        try (InputStream responseStream = connection.getResponseCode() == 200 ? connection.getInputStream() : connection.getErrorStream();
             Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8)) {
            return (JSONObject) new JSONParser().parse(reader);
        } catch (Exception e) {
            logger.error("Error reading response", e);
            JSONObject errorResponse = new JSONObject();
            errorResponse.put("error", "Error reading response");
            return errorResponse;
        }
    }

    private HttpURLConnection createConnection(String secretKey, String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Authorization", "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes(StandardCharsets.UTF_8)));
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        return connection;
    }

    private void saveTossPayment(JSONObject tossResponse, Long buyerId, Long storeId, Long productId) {

        // 주입받은 repository를 사용
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));
        Store seller = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException("Store not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // TossPayment 엔티티 생성
        TossPayment tossPayment = TossPayment.from(
                buyerId,
                storeId,
                productId,
                (String) tossResponse.get("orderId"),
                (String) tossResponse.get("orderName"),
                (String) tossResponse.get("paymentKey"),
                (String) tossResponse.get("status"),
                Long.parseLong(tossResponse.get("totalAmount").toString()),
                (String) tossResponse.get("approvedAt"),
                (String) tossResponse.get("requestedAt"),
                buyer,
                seller,
                product
        );

        tossPaymentRepository.save(tossPayment);
        logger.info("Toss Payment saved successfully: {}", tossPayment);
    }

}