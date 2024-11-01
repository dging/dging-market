package com.dging.dgingmarket.web.api.dto.chat;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  채팅 메시지 생성 요청 DTO
 *  - 웹소켓에서 사용하기 때문에 이미지 파일은 직접적으로 포함할 수 없음
 *  - 파일 업로드 API를 먼저 호출 후, 응답받은 URL을 포함시키는 형태로 요청 DTO 작성
 */
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChatMessageCreateRequest {
    private Long chatRoomId;
    private String content;
    private String imageUrl;
}
