package com.dging.dgingmarket.web.socket.dto;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.*;

@Getter
@Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("chat-room-info")
public class RedisChatRoomInfo {
    @Id
    private Long id;
    private Set<Long> currentUserIds;
    private Set<Long> userIds;
    private Map<Long, Date> lastUserConnectedAts;
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
        if(this.currentUserIds == null) {
            currentUserIds = new HashSet<>();
        }
        this.currentUserIds.add(userId);
        updateConnection(userId);
    }

    public void removeCurrentUser(Long userId) {
        this.currentUserIds.remove(userId);
        updateConnection(userId);
    }

    public int getCurrentUserCount() {
        return this.currentUserIds.size();
    }

    public boolean hasUser(Long userId) {
        return this.userIds != null && this.userIds.contains(userId);
    }

    public Date getLastUserConnectedAt(Long userId) {
        if(this.lastUserConnectedAts != null) {
            return this.lastUserConnectedAts.get(userId);
        }

        return null;
    }

    public void updateConnection(Long userId) {
        if(this.lastUserConnectedAts == null) {
            this.lastUserConnectedAts = new HashMap<>();
        }
        this.lastUserConnectedAts.put(userId, new Date());
    }
}
