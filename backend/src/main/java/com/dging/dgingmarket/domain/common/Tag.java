package com.dging.dgingmarket.domain.common;

import com.dging.dgingmarket.domain.product.ProductTag;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_TAG")
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private int usageCount;

    public static Tag create(String name) {
        Tag tag = new Tag();
        tag.setName(name);
        return tag;
    }

    public void used() {
        usageCount += 1;
    }
}
