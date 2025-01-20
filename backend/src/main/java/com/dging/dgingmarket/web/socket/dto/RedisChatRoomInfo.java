package com.dging.dgingmarket.web.socket.dto;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("chat-room-info")
public class RedisChatRoomInfo {
    @Id
    private Long id;
    private Set<Long> currentUserIds;
    private Set<Long> userIds;
    private long userCount;
    @Setter
    private long messageCount;

    public RedisChatRoomInfo(Long id) {
        this.id = id;
    }

    public static RedisChatRoomInfo create(Long id, Long purchaserId, Long sellerId) {
        RedisChatRoomInfo chatRoomInfo = new RedisChatRoomInfo(id);

        HashSet<Long> userIds = new HashSet<>();
        userIds.add(purchaserId);
        userIds.add(sellerId);

        chatRoomInfo.setUserIds(userIds);

        return chatRoomInfo;
    }

    public void addCurrentUser(Long userId) {
        if(currentUserIds == null) {
            currentUserIds = new HashSet<>();
        }
        this.currentUserIds.add(userId);
        this.userCount = this.currentUserIds.size();
    }

    public void removeCurrentUser(Long userId) {
        this.currentUserIds.remove(userId);
        this.userCount = this.currentUserIds.size();
    }

    public boolean hasUser(Long userId) {
        return this.userIds.contains(userId);
    }
}
