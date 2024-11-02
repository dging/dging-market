package com.dging.dgingmarket.domain.product;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductTagId implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "tag_id")
    private Long tagId;
}
