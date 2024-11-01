package com.dging.dgingmarket.domain.common;

import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.listener.IpEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_IMAGE")
@EntityListeners({AuditingEntityListener.class})
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String type;

    @Column(length = 200, nullable = false)
    private String fileName;

    @Column(length = 500, nullable = false)
    private String path;

    @Column(length = 500, nullable = false)
    private String url;

    @Column(nullable = false)
    private int size;

    @CreatedDate
    @Column(length = 6, nullable = false)
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private Long userId;

    public static Image create(User user, String type, String fileName, String path, String url, int size) {
        Image image = new Image();
        image.setUser(user);
        image.setType(type);
        image.setFileName(fileName);
        image.setPath(path);
        image.setUrl(url);
        image.setSize(size);;
        return image;
    }
}
