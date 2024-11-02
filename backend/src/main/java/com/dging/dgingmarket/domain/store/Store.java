package com.dging.dgingmarket.domain.store;

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
import java.util.UUID;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_STORE")
@EntityListeners({AuditingEntityListener.class})
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String name;

    @Column(length = 500)
    private String introduction;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @CreatedDate
    @Column(length = 6, nullable = false)
    private Date createdAt;

    @LastModifiedDate
    @Column(length = 6, nullable = false)
    private Date updatedAt;

    public static Store create(User user, String name, String introduction) {
        Store store = new Store();
        store.setUser(user);
        store.setName(name);
        store.setIntroduction(introduction);
        return store;
    }

    public static Store createEmpty(User user) {
        return create(user, UUID.randomUUID().toString(), null);
    }

    public void updateName(String name) {
        this.name = name;
    }

    public void updateIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
