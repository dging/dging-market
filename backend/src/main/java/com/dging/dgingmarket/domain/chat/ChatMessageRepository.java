package com.dging.dgingmarket.domain.chat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
    List<ChatMessage> findByChatRoomIdOrderByCreatedAt(Long roomId);

    int countByChatRoomId(Long chatRoomId);

    @Modifying
    @Query("UPDATE ChatMessage c SET c.read = true WHERE c.chatRoom.id = :chatRoomId AND c.read = false")
    int updateAllReadStatus(Long chatRoomId);

    @Modifying
    @Query("UPDATE ChatMessage c SET c.read = true WHERE c.sender.id != :senderId AND c.chatRoom.id = :chatRoomId AND c.read = false")
    int updateReadStatus(Long senderId, Long chatRoomId);
}
