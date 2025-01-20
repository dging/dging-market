package com.dging.dgingmarket.web.socket.dto;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("chat-room-info")
public class RedisChatRoomInfo {
    @Id
    private Long id;
    private Set<Long> userIds;
    private long userCount;
    @Setter
    private long messageCount;

    public RedisChatRoomInfo(Long id) {
        this.id = id;
    }

    public static RedisChatRoomInfo create(Long id) {
        return new RedisChatRoomInfo(id);
    }

    public void addUser(Long userId) {
        if(userIds == null) {
            userIds = new HashSet<>();
        }
        this.userIds.add(userId);
        this.userCount = this.userIds.size();
    }

    public void removeUser(Long userId) {
        this.userIds.remove(userId);
        this.userCount = this.userIds.size();
    }
}
