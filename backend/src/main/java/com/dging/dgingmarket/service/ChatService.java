package com.dging.dgingmarket.service;

import com.dging.dgingmarket.domain.chat.*;
import com.dging.dgingmarket.domain.chat.exception.ChatMyselfException;
import com.dging.dgingmarket.domain.chat.exception.ChatRoomNotFoundException;
import com.dging.dgingmarket.domain.chat.exception.UserOwnChatRoomException;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.product.ProductRepository;
import com.dging.dgingmarket.domain.product.exception.ProductNotFoundException;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.domain.user.exception.UserNotFoundException;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.web.api.dto.chat.ChatRoomEnterResponse;
import com.dging.dgingmarket.web.socket.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final RedisChatMessageRepository redisChatMessageRepository;
    private final RedisChatRoomInfoRepository redisChatRoomInfoRepository;
    private final RedisTemplate<String, RedisChatMessage> redisTemplate;

    @Transactional
    public void join(RedisChatMessage message) {
        ChatRoom foundChatRoom = chatRoomRepository.findById(message.getRoomId()).orElseThrow(ChatRoomNotFoundException::new);
        Long chatRoomId = foundChatRoom.getId();

        User sender = EntityUtils.userThrowable();
        Long senderId = sender.getId();

        RedisChatRoomInfo foundChatRoomInfo = redisChatRoomInfoRepository.findById(chatRoomId).orElseThrow(ChatRoomNotFoundException::new);

        foundChatRoomInfo.addCurrentUser(senderId);
        redisChatRoomInfoRepository.save(foundChatRoomInfo);

        long connectedUserCount = foundChatRoomInfo.getUserCount();
        int updateCount = 0;
        if (connectedUserCount == 2) {
            updateCount = chatMessageRepository.updateAllReadStatus(chatRoomId);
        } else if (connectedUserCount == 1) {
            updateCount = chatMessageRepository.updateReadStatus(senderId, chatRoomId);
        }

        if(updateCount > 0) {
            List<RedisChatMessage> chatMessages = chatMessageRepository.findByChatRoomIdOrderByCreatedAt(chatRoomId)
                    .stream()
                    .map((chatMessage) -> RedisChatMessage.of(chatMessage, MessageType.TALK))
                    .toList();

            redisChatMessageRepository.saveAll(chatMessages);

            RedisChatReadMessages chatReadMessages = RedisChatReadMessages.of(chatRoomId, chatMessages);

            redisTemplate.convertAndSend("chat:read", chatReadMessages);
        }
    }

    @Transactional
    public void leave(Long chatRoomId, Long userId) {
        RedisChatRoomInfo chatRoomInfo = redisChatRoomInfoRepository.findById(chatRoomId).orElseThrow(ChatRoomNotFoundException::new);

        chatRoomInfo.removeCurrentUser(userId);
        redisChatRoomInfoRepository.save(chatRoomInfo);
    }

    @Transactional
    public void sendMessage(RedisChatMessage message) {
        Long chatRoomId = message.getRoomId();

        ChatRoom foundChatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(ChatRoomNotFoundException::new);

        User sender = EntityUtils.userThrowable();

        ChatMessage chatMessage = ChatMessage.create(sender, foundChatRoom, message.getContent());

        RedisChatRoomInfo foundRedisChatRoomInfo = redisChatRoomInfoRepository.findById(chatRoomId).orElseThrow(ChatRoomNotFoundException::new);

        long connectedUserCount = foundRedisChatRoomInfo.getUserCount();
        if(connectedUserCount == 2) {
            chatMessageRepository.updateAllReadStatus(chatRoomId);
            chatMessage.read();
        }

        ChatMessage createdMessage = chatMessageRepository.save(chatMessage);
        RedisChatMessage redisChatMessage = RedisChatMessage.of(createdMessage, message.getType());

        int messageCount = chatMessageRepository.countByChatRoomId(chatRoomId);

        foundRedisChatRoomInfo.setMessageCount(messageCount);
        redisChatRoomInfoRepository.save(foundRedisChatRoomInfo);
        redisChatMessageRepository.save(redisChatMessage);

        // Redis pub/sub으로 메시지 발행
        redisTemplate.convertAndSend("chat:message", redisChatMessage);
    }

    @Transactional
    public List<RedisChatMessage> chatMessages(Long roomId) {
        RedisChatRoomInfo foundRedisChatRoomInfo = redisChatRoomInfoRepository.findById(roomId).orElseThrow(ChatRoomNotFoundException::new);

        User user = EntityUtils.userThrowable();

        validateUserOwnChatRoom(foundRedisChatRoomInfo, user.getId());

        List<RedisChatMessage> foundRedisChatMessages = redisChatMessageRepository.findByRoomId(roomId)
                .stream()
                .sorted(Comparator.comparing(RedisChatMessage::getTimestamp))
                .collect(Collectors.toList());

        int redisMessageCount = foundRedisChatMessages.size();
        long messageCount = foundRedisChatRoomInfo.getMessageCount();

        if(redisMessageCount < messageCount) {
            List<RedisChatMessage> chatMessages = chatMessageRepository.findByChatRoomIdOrderByCreatedAt(roomId)
                    .stream()
                    .map((chatMessage) -> RedisChatMessage.of(chatMessage, MessageType.TALK))
                    .toList();
            redisChatMessageRepository.saveAll(chatMessages);
            return chatMessages;
        }

        return foundRedisChatMessages;
    }

    @Transactional
    public ChatRoomEnterResponse enterRoom(Long productId) {
        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(productId).orElseThrow(ProductNotFoundException::new);

        User purchaser = EntityUtils.userThrowable();
        User foundPurchaser = userRepository.findById(purchaser.getId()).orElseThrow(UserNotFoundException::new);
        User seller = foundProduct.getStore().getUser();

        validateChatMyself(foundPurchaser.getId(), seller.getId());

        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findByFromAndToAndProduct(purchaser, seller, foundProduct);

        final ChatRoomEnterResponse response;

        if(chatRoomOptional.isEmpty()) {
            ChatRoom chatRoomToCreate = ChatRoom.create(foundPurchaser, seller, foundProduct);
            ChatRoom createdChatRoom = chatRoomRepository.saveAndFlush(chatRoomToCreate);

            response = ChatRoomEnterResponse.create(createdChatRoom.getId(), 201);
        } else {
            ChatRoom foundChatRoom = chatRoomOptional.get();
            response = ChatRoomEnterResponse.create(foundChatRoom.getId(), 200);
        }

        RedisChatRoomInfo redisChatRoomInfoToCreate = RedisChatRoomInfo.create(response.getId(), purchaser.getId(), seller.getId());
        redisChatRoomInfoRepository.save(redisChatRoomInfoToCreate);

        return response;
    }

    // 본인의 채팅방이 아님
    private static void validateUserOwnChatRoom(RedisChatRoomInfo redisChatRoomInfo, Long userId) {
        if(!redisChatRoomInfo.hasUser(userId)) {
            throw UserOwnChatRoomException.EXCEPTION;
        }
    }

    // 본인과 채팅할 수 없음
    private static void validateChatMyself(Long purchaserId, Long sellerId) {
        if(Objects.equals(purchaserId, sellerId)) {
            throw ChatMyselfException.EXCEPTION;
        }
    }

    /*private void refreshChatMessages(List<RedisChatMessage> chatMessages) {
        redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            chatMessages.stream().map(RedisChatMessage::getId).forEach(id -> {
                String key = "chat-message:" + id;
                connection.expire(key.getBytes(), 10L);
            });
            return null;
        });
    }*/
}