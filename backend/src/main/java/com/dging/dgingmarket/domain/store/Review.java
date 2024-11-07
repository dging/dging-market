package com.dging.dgingmarket.domain.store;

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
@Table(name = "TBL_REVIEW")
@EntityListeners({AuditingEntityListener.class})
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, updatable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, updatable = false)
    private Product product;

    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Long userId;

    @Column(length = 500)
    private String content;

    @Column(nullable = false)
    private int rate;

    @CreatedDate
    @Column(length = 6, nullable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(length = 6, nullable = false)
    private Date updatedAt;

    public static Review create(User user, Product product, String content, int rate) {
        Review review = new Review();
        review.setUser(user);
        review.setStore(product.getStore());
        review.setProduct(product);
        review.setContent(content);
        review.setRate(rate);
        return review;
    }

}
