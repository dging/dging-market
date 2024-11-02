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
@Table(
        name = "TBL_CHAT_MESSAGE",
        uniqueConstraints = {
                @UniqueConstraint(
                        name="chat_message_uk",
                        columnNames = {"from_user_fk", "to_user_fk"}
                )
        }
)
@EntityListeners({AuditingEntityListener.class})
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_fk", nullable = false)
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_fk", nullable = false)
    private User to;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @Column(length = 2000, nullable = false)
    private String content;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean isRead;

    @CreatedDate
    @Column(length = 6)
    private Date createdAt;

    @LastModifiedDate
    @Column(length = 6)
    private Date updatedAt;

    public static ChatMessage create(User from, User to, String content) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setFrom(from);
        chatMessage.setTo(to);
        chatMessage.setContent(content);
        chatMessage.setRead(false);
        return chatMessage;
    }

    public void read() {
        this.isRead = true;
    }
}
