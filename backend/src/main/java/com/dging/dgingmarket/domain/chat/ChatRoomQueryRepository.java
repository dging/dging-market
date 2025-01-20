package com.dging.dgingmarket.domain.chat;

import com.dging.dgingmarket.web.api.dto.chat.ChatRoomResponse;

import java.util.Optional;

public interface ChatRoomQueryRepository {

    Optional<ChatRoomResponse> room(Long id, Long userId);
}
