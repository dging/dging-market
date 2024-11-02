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
public class ProductImageId implements Serializable {

    @Column(name = "image_id")
    private Long imageId;

    @Column(name = "product_id")
    private Long productId;
}
