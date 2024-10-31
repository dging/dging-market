package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter(AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "TBL_FOLLOWER",
        uniqueConstraints = {
                @UniqueConstraint(
                        name="follower_uk",
                        columnNames = {"from_user_fk", "to_user_fk"}
                )
        }
)
@EntityListeners({AuditingEntityListener.class})
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_fk")
    private User from;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_fk")
    private User to;

    @CreatedDate
    @Column(length = 6, nullable = false)
    private Date createdAt;

    public static Follower create(User from, User to) {
        Follower follower = new Follower();
        follower.setFrom(from);
        follower.setTo(to);
        return follower;
    }
}
