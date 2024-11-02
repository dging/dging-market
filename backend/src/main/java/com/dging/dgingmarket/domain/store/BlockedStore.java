package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.domain.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_BLOCKED_STORE")
@IdClass(BlockedStoreId.class)
@EntityListeners({AuditingEntityListener.class})
public class BlockedStore {

    @Id
    @Column(name = "store_id")
    private Long storeId;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false, insertable = false, updatable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable = false, updatable = false)
    private User user;

    @CreatedDate
    @Column(length = 6, nullable = false)
    private Date createdAt;

    public static BlockedStore create(Store store, User user) {
        BlockedStore blockedStore = new BlockedStore();
        blockedStore.setStore(store);
        blockedStore.setUser(user);
        return blockedStore;
    }
}
