package com.dging.dgingmarket.domain.product;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProductTagId implements Serializable {

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "tag_id")
    private Long tagId;
}
