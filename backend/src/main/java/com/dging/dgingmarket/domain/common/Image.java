package com.dging.dgingmarket.domain.common;

import com.dging.dgingmarket.listener.IpEntityListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_IMAGE")
@DiscriminatorColumn(name = "type")
@Inheritance(strategy = InheritanceType.JOINED)
@EntityListeners({AuditingEntityListener.class})
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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
}
