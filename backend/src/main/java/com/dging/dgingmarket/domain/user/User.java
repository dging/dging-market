package com.dging.dgingmarket.domain.user;

import com.dging.dgingmarket.domain.common.enums.Role;
import com.dging.dgingmarket.listener.CreationIp;
import com.dging.dgingmarket.listener.IpEntityListener;
import com.dging.dgingmarket.listener.UpdateIp;
import com.dging.dgingmarket.util.manager.SeqManager;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static javax.persistence.FetchType.EAGER;

@DynamicInsert
@DynamicUpdate
@Entity
@Getter @Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_USER")
@EntityListeners({AuditingEntityListener.class, IpEntityListener.class})
public class User implements UserDetails {

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_manager_user")
    @GenericGenerator(name = "seq_manager_user", strategy = "com.dging.dgingmarket.util.manager.SeqManager", parameters = {
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_USER_SEQ_PARAMETER, value = "user_"),
            @org.hibernate.annotations.Parameter(name = SeqManager.NUMBER_FORMAT_PARAMETER, value = "%010d"),
            @org.hibernate.annotations.Parameter(name = SeqManager.VALUE_COLUMN_PARAM, value = "seq"),
            @org.hibernate.annotations.Parameter(name = SeqManager.OPT_PARAM, value = "pooled"),
            @org.hibernate.annotations.Parameter(name = SeqManager.INITIAL_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = SeqManager.INCREMENT_PARAM, value = "1000")
    })
    @Column(nullable = false, updatable = false, length = 15)
    @Id
    private String seq;

    @Column(length = 20, unique = true)
    private String userId;

    @Column(length = 200)
    private String password;

    @Column
    private String socialId;

    @Column(length = 20)
    private String username;

    @Column(length = 20)
    private String provider;

    @Column(length = 500)
    private String thumbnailUrl;

    @Column
    private String email;

    @Column
    private String phoneNo;

    @ElementCollection(fetch = EAGER)
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean isAuthenticated;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean isNewsOptedIn;

    @Column
    private String fcmToken;

    @Column(nullable = false, columnDefinition = "tinyint(1) default 0")
    private boolean isExpired;

    @Column(length = 6)
    private Date expiredAt;

    @CreatedDate
    @Column(length = 6)
    private Date createdAt;

    @CreationIp
    @Column(length = 20)
    private String creationIp;

    @Column(length = 6)
    private Date withdrawnAt;

    @LastModifiedDate
    @Column(length = 6)
    private Date updatedAt;

    @UpdateIp
    @Column(length = 20)
    private String updateIp;

    @Column(length = 6)
    private Date loggedInAt;

    @Column(length = 6)
    private Date loggedOutAt;

    public static User create(String id, String password, String username) {
        User user = new User();
        user.setUserId(id);
        user.setPassword(password);
        user.setUsername(username);
        return user;
    }

    public static User createSocial(String password, String provider, String snsId, String username, String thumbnailUrl) {
        User user = create(provider + RandomStringUtils.random(20-provider.length(), true, true), password, username);
        user.setRoles(Collections.singletonList(Role.USER));
        user.setProvider(provider);
        user.setSocialId(snsId);
        user.setThumbnailUrl(thumbnailUrl);
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles
                .stream().map((role) -> new SimpleGrantedAuthority(role.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
