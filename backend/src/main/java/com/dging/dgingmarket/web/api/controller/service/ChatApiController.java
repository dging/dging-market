package com.dging.dgingmarket.web.api.controller.service;

import com.dging.dgingmarket.documentation.ApiErrorCodeExample;
import com.dging.dgingmarket.exception.ChatErrorCode;
import com.dging.dgingmarket.service.ChatService;
import com.dging.dgingmarket.util.constant.DocumentDescriptions;
import com.dging.dgingmarket.web.api.dto.chat.ChatRoomEnterResponse;
import com.dging.dgingmarket.web.socket.dto.RedisChatMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.dging.dgingmarket.exception.ChatErrorCode.*;
import static com.dging.dgingmarket.exception.ProductErrorCode._PRODUCT_NOT_FOUND;
import static com.dging.dgingmarket.exception.UserErrorCode._USER_NOT_FOUND;

@Slf4j
@RequestMapping
@RequiredArgsConstructor
@Tag(name = "5. 채팅", description = "채팅 API 엔드포인트")
@RestController
public class ChatApiController {
    private final ChatService chatService;

    @PostMapping("/products/{id}/chat-room")
    @Operation(summary = "채팅방 입장", description = "판매 상품에 대해 판매자와의 채팅 내역이 없다면 채팅방을 생성합니다.")
    @ApiResponses({@ApiResponse(responseCode = "200", description = "성공"), @ApiResponse(responseCode = "201", description = "성공")})
    @ApiErrorCodeExample({
            _USER_NOT_FOUND,
            _PRODUCT_NOT_FOUND,
            _CHAT_MYSELF_ERROR,
    })
    ResponseEntity<ChatRoomEnterResponse> create(
            @Parameter(description = DocumentDescriptions.REQUEST_PRODUCT_ID)
            @PathVariable Long id
    ) {
        ChatRoomEnterResponse response = chatService.enterRoom(id);

        if(response.getCode() == 201) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }

    @GetMapping("/chat-rooms/{id}/messages")
    @Operation(summary = "채팅 조회", description = "여러 채팅을 조회합니다.")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "성공"))
    public ResponseEntity<List<RedisChatMessage>> fetchChatMessages(
            @Parameter(description = DocumentDescriptions.REQUEST_CHAT_ROOM_ID)
            @PathVariable Long id
    ) {
        List<RedisChatMessage> messages = chatService.chatMessages(id);
        return ResponseEntity.ok(messages);
    }
}
