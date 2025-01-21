package com.dging.dgingmarket.service;

import com.dging.dgingmarket.domain.chat.ChatMessage;
import com.dging.dgingmarket.domain.chat.ChatMessageRepository;
import com.dging.dgingmarket.domain.chat.ChatRoom;
import com.dging.dgingmarket.domain.chat.ChatRoomRepository;
import com.dging.dgingmarket.domain.chat.exception.ChatMyselfException;
import com.dging.dgingmarket.domain.chat.exception.ChatRoomNotFoundException;
import com.dging.dgingmarket.domain.chat.exception.UserOwnChatRoomException;
import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.product.ProductRepository;
import com.dging.dgingmarket.domain.product.exception.ProductNotFoundException;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.web.api.dto.chat.ChatRoomEnterResponse;
import com.dging.dgingmarket.web.api.dto.chat.ChatRoomResponse;
import com.dging.dgingmarket.web.api.dto.chat.ChatRoomsResponse;
import com.dging.dgingmarket.web.socket.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatService {
    private final ProductRepository productRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;
    private final RedisChatMessageRepository redisChatMessageRepository;
    private final RedisChatRoomInfoRepository redisChatRoomInfoRepository;
    private final RedisTemplate<String, RedisChatMessage> redisTemplate;

    @Transactional
    public void join(RedisChatMessage message) {
        // 채팅방 가져오기. 없으면 예외 발생.
        ChatRoom foundChatRoom = chatRoomRepository.findById(message.getRoomId()).orElseThrow(ChatRoomNotFoundException::new);
        Long chatRoomId = foundChatRoom.getId();

        User sender = EntityUtils.userThrowable();
        Long senderId = sender.getId();

        // Redis에서 채팅방 정보 가져오기. 없으면 DB에서 조회 후 갱신.
        RedisChatRoomInfo foundChatRoomInfo = redisChatRoomInfoRepository.findById(chatRoomId).orElseGet(() -> savedRedisChatRoomInfo(chatRoomId));

        // Redis에 현재 채팅방에 접속 중인 상태 업데이트
        foundChatRoomInfo.addCurrentUser(senderId);
        redisChatRoomInfoRepository.save(foundChatRoomInfo);

        // 현재 채팅방에 접속 중인 사용자 수에 따라 읽음 수 갱신
        long connectedUserCount = foundChatRoomInfo.getCurrentUserCount();
        int updateCount = 0;
        if (connectedUserCount == 2) {
            // 모두 채팅방에 접속해 있다면 읽지 않은 메시지는 모두 읽음 처리
            updateCount = chatMessageRepository.updateAllReadStatus(chatRoomId);
        } else if (connectedUserCount == 1) {
            // 채팅방에 현재 사용자만 접속해있다면 본인이 읽지 않은 메시지만 읽음 처리
            updateCount = chatMessageRepository.updateReadStatus(senderId, chatRoomId);
        }

        // 갱신된 레코드가 있다면 상대방에게 상태 변경 메시지 발행
        if(updateCount > 0) {
            List<RedisChatMessage> chatMessages = chatMessageRepository.findByChatRoomIdOrderByCreatedAt(chatRoomId)
                    .stream()
                    .map((chatMessage) -> RedisChatMessage.of(chatMessage, MessageType.TALK))
                    .toList();

            redisChatMessageRepository.saveAll(chatMessages);

            RedisChatReadMessages chatReadMessages = RedisChatReadMessages.create(chatRoomId, chatMessages);

            redisTemplate.convertAndSend("chat:read", chatReadMessages);

        }

        // 상대방에게 접속 상태 변경 메시지 발행
        Date lastUserConnectedAt = foundChatRoomInfo.getLastUserConnectedAt(senderId);
        RedisChatAccessMessage chatAccessMessage = RedisChatAccessMessage.create(chatRoomId, senderId, lastUserConnectedAt, true);

        redisTemplate.convertAndSend("chat:access", chatAccessMessage);
    }

    // 웹 소켓 세션 종료 시 호출
    @Transactional
    public void leave(Long chatRoomId, Long userId) {
        // Redis에서 채팅방 정보 가져오기. 없으면 DB에서 조회 후 갱신.
        RedisChatRoomInfo chatRoomInfo = redisChatRoomInfoRepository.findById(chatRoomId).orElseGet(() -> savedRedisChatRoomInfo(chatRoomId));

        // Redis에 현재 채팅방에 접속 중이 아닌 상태 업데이트
        chatRoomInfo.removeCurrentUser(userId);
        redisChatRoomInfoRepository.save(chatRoomInfo);

        Date lastUserConnectedAt = chatRoomInfo.getLastUserConnectedAt(userId);
        RedisChatAccessMessage chatAccessMessage = RedisChatAccessMessage.create(chatRoomId, userId, lastUserConnectedAt, false);

        // 상대방에게 접속 상태 변경 메시지 발행
        redisTemplate.convertAndSend("chat:access", chatAccessMessage);
    }

    @Transactional
    public void sendMessage(RedisChatMessage message) {
        Long chatRoomId = message.getRoomId();

        // 채팅방 가져오기. 없으면 예외 발생.
        ChatRoom foundChatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(ChatRoomNotFoundException::new);

        User sender = EntityUtils.userThrowable();

        ChatMessage chatMessage = ChatMessage.create(sender, foundChatRoom, message.getContent());

        // Redis에서 채팅방 정보 가져오기. 없으면 DB에서 조회 후 갱신.
        RedisChatRoomInfo foundRedisChatRoomInfo = redisChatRoomInfoRepository.findById(chatRoomId).orElseGet(() -> savedRedisChatRoomInfo(chatRoomId));

        // 현재 채팅방에 접속 중인 사용자 수에 따라 읽음 수 갱신
        long connectedUserCount = foundRedisChatRoomInfo.getCurrentUserCount();
        if(connectedUserCount == 2) {
            // 모두 채팅방에 접속해 있다면 읽지 않은 메시지는 모두 읽음 처리
            chatMessageRepository.updateAllReadStatus(chatRoomId);
            // 변경된 상태를 바로 사용하기 위해 현재 메시지를 따로 읽음 처리
            chatMessage.read();
        }

        // DB에 메시지 저장
        ChatMessage createdMessage = chatMessageRepository.save(chatMessage);
        // Redis 저장용 메시지 생성
        RedisChatMessage redisChatMessage = RedisChatMessage.of(createdMessage, message.getType());

        // 캐싱
        redisChatRoomInfoRepository.save(foundRedisChatRoomInfo);
        redisChatMessageRepository.save(redisChatMessage);

        // Redis Pub/Sub으로 상대방에게 메시지 발행
        redisTemplate.convertAndSend("chat:message", redisChatMessage);
    }

    @Transactional
    public List<RedisChatMessage> chatMessages(Long roomId) {
        // Redis에서 채팅방 정보 가져오기. 없으면 DB에서 조회 후 갱신.
        RedisChatRoomInfo chatRoomInfo = redisChatRoomInfoRepository.findById(roomId).orElseGet(() -> savedRedisChatRoomInfo(roomId));

        User user = EntityUtils.userThrowable();

        // 본인의 채팅방인지 확인
        validateUserOwnChatRoom(chatRoomInfo, user.getId());

        // Redis에서 채팅을 가져온 후 날짜 기준 오름차순 정렬
        List<RedisChatMessage> foundRedisChatMessages = redisChatMessageRepository.findByRoomId(roomId)
                .stream()
                .sorted(Comparator.comparing(RedisChatMessage::getTimestamp))
                .collect(Collectors.toList());

        int redisMessageCount = foundRedisChatMessages.size();
        long messageCount = chatMessageRepository.countByChatRoomId(roomId);

        // Redis에서 만료된 데이터가 있다면 DB에서 캐싱 후 데이터 반환
        if(redisMessageCount < messageCount) {
            List<RedisChatMessage> chatMessages = chatMessageRepository.findByChatRoomIdOrderByCreatedAt(roomId)
                    .stream()
                    .map((chatMessage) -> RedisChatMessage.of(chatMessage, MessageType.TALK))
                    .toList();
            redisChatMessageRepository.saveAll(chatMessages);
            return chatMessages;
        }

        // 캐싱 데이터 그대로 반환
        return foundRedisChatMessages;
    }

    @Transactional
    public ChatRoomEnterResponse enterRoom(Long productId) {
        // 상품 가져오기. 없으면 예외 발생.
        Product foundProduct = productRepository.findByIdAndDeletedIsFalse(productId).orElseThrow(ProductNotFoundException::new);

        // 구매자(사용자 본인)
        User purchaser = EntityUtils.userThrowable();

        // 판매자
        User seller = foundProduct.getStore().getUser();

        // 사용자 본인과 채팅을 시도하려는지 확인
        validateChatMyself(purchaser.getId(), seller.getId());

        // 유니크 키를 가지고 채팅방 가져오기
        Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findByFromAndToAndProduct(purchaser, seller, foundProduct);

        final ChatRoomEnterResponse response;

        if(chatRoomOptional.isEmpty()) {
            // 채팅방이 없으면 생성 후 응답 DTO 생성
            ChatRoom chatRoomToCreate = ChatRoom.create(purchaser, seller, foundProduct);
            ChatRoom createdChatRoom = chatRoomRepository.saveAndFlush(chatRoomToCreate);

            response = ChatRoomEnterResponse.create(createdChatRoom.getId(), 201);
        } else {
            // 채팅방이 있으면 그대로 응답 DTO 생성
            ChatRoom foundChatRoom = chatRoomOptional.get();
            response = ChatRoomEnterResponse.create(foundChatRoom.getId(), 200);
        }

        // Redis AOF 저장 방식을 통해 동기화 가능
        // Redis에 채팅방 정보 캐싱
        RedisChatRoomInfo redisChatRoomInfoToCreate = RedisChatRoomInfo.create(response.getId(), purchaser.getId(), seller.getId());
        redisChatRoomInfoRepository.save(redisChatRoomInfoToCreate);

        return response;
    }

    public List<ChatRoomsResponse> rooms() {
        User user = EntityUtils.userThrowable();
        return chatRoomRepository.findByFromOrTo(user, user)
                .stream().map(chatRoom -> ChatRoomsResponse.of(user, chatRoom)).toList();
    }

    @Transactional
    public ChatRoomResponse room(Long id) {
        User user = EntityUtils.userThrowable();
        Long userId = user.getId();

        ChatRoomResponse response = chatRoomRepository.room(id, userId).orElseThrow(ChatRoomNotFoundException::new);

        RedisChatRoomInfo chatRoomInfo = redisChatRoomInfoRepository.findById(id).orElseGet(() -> savedRedisChatRoomInfo(id));
        response.setLastRecipientConnectedAt(chatRoomInfo.getLastUserConnectedAt(userId));

        validateUserOwnChatRoom(chatRoomInfo, userId);

        return response;
    }

    private RedisChatRoomInfo savedRedisChatRoomInfo(Long roomId) {
        ChatRoom foundChatRoom = chatRoomRepository.findById(roomId).orElseThrow(ChatRoomNotFoundException::new);
        Long purchaserId = foundChatRoom.getFrom().getId();
        Long sellerId = foundChatRoom.getTo().getId();
        RedisChatRoomInfo redisChatRoomInfoToCreate = RedisChatRoomInfo.create(roomId, purchaserId, sellerId);
        return redisChatRoomInfoRepository.save(redisChatRoomInfoToCreate);
    }

    private static void validateUserOwnChatRoom(RedisChatRoomInfo redisChatRoomInfo, Long userId) {
        if(!redisChatRoomInfo.hasUser(userId)) {
            throw UserOwnChatRoomException.EXCEPTION;
        }
    }

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