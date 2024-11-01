package com.dging.dgingmarket.domain.refreshtoken;

import com.dging.dgingmarket.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "tbl_refresh_token")
public class RefreshToken extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token_key", nullable = false, updatable = false)
    private Long key;

    @Column(nullable = false)
    private String token;

    public void update(String token) {
        this.token = token;
    }

    public static RefreshToken create(Long key, String token) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setKey(key);
        refreshToken.setToken(token);
        return refreshToken;
    }
}
