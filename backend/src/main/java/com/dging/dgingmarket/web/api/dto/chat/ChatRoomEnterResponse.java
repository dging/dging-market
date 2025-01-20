package com.dging.dgingmarket.web.api.dto.chat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.dging.dgingmarket.util.constant.DocumentDescriptions.RESPONSE_ID;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Schema(description = "채팅방 입장 응답 DTO")
public class ChatRoomEnterResponse {

    @Schema(description = RESPONSE_ID)
    private Long id;

    @JsonIgnore
    private int code;

    public static ChatRoomEnterResponse create(Long id, int code) {
        ChatRoomEnterResponse response = new ChatRoomEnterResponse();
        response.setId(id);
        response.setCode(code);
        return response;
    }
}
