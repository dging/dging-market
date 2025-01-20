package com.dging.dgingmarket.domain.chat;

import com.dging.dgingmarket.domain.product.Product;
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
        name = "TBL_CHAT_ROOM",
        uniqueConstraints = {
                @UniqueConstraint(
                        name="chat_room_uk",
                        columnNames = {"from_user_fk", "to_user_fk", "product_id"}
                )
        }
)
@EntityListeners({AuditingEntityListener.class})
public class ChatRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 구매자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_fk", nullable = false)
    private User from;

    // 판매자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_fk", nullable = false)
    private User to;

    // 판매 상품
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean deleted;

    @CreatedDate
    @Column(length = 6)
    private Date createdAt;

    @LastModifiedDate
    @Column(length = 6)
    private Date updatedAt;

    public static ChatRoom create(User from, User to, Product product) {
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setFrom(from);
        chatRoom.setTo(to);
        chatRoom.setProduct(product);
        return chatRoom;
    }

    public void delete() {
        this.deleted = true;
    }
}
