package com.dging.dgingmarket.domain.product;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProductTagId implements Serializable {

    private Long productId;
    private Long tagId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductTagId)) return false;
        ProductTagId that = (ProductTagId) o;
        return productId.equals(that.productId) && tagId.equals(that.tagId);
    }

    @Override
    public int hashCode() {
        return productId.hashCode() + tagId.hashCode();
    }
}
