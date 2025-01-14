package DGM.banking.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    // 결제 페이지 (결제창 요청 화면)
    @GetMapping("/payment/checkout")
    public String checkout() {
        return "payment/checkout";
    }

    // 결제 성공 시 리다이렉트 후 응답 처리
    @GetMapping("/payment/success")
    public String paymentSuccess(String orderId, String paymentKey, String amount) {
        // 결제 성공 후 추가 처리 로직을 넣을 수 있습니다.
        return "payment/success"; // 성공 화면으로 리다이렉트
    }

    // 결제 실패 시 리다이렉트 후 응답 처리
    @GetMapping("/payment/failure")
    public String paymentFailure() {
        return "fail"; // 실패 화면으로 리다이렉트
    }
}
