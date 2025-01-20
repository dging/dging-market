package com.dging.dgingmarket.domain.chat;

import com.dging.dgingmarket.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_CHAT_MESSAGE")
@EntityListeners({AuditingEntityListener.class})
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(name = "is_read", nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean read;

    @CreatedDate
    @Column(length = 6)
    private Date createdAt;

    @LastModifiedDate
    @Column(length = 6)
    private Date updatedAt;

    public static ChatMessage create(User sender, ChatRoom chatRoom, String content) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSender(sender);
        chatMessage.setChatRoom(chatRoom);
        chatMessage.setContent(content);
        chatMessage.setRead(false);
        return chatMessage;
    }

    public void read() {
        this.read = true;
    }
}
