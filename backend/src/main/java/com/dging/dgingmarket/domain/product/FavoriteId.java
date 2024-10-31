package com.dging.dgingmarket.domain.product;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FavoriteId implements Serializable {

    private Long userId;
    private Long productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FavoriteId)) return false;
        FavoriteId that = (FavoriteId) o;
        return userId.equals(that.userId) && productId.equals(that.productId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode() + productId.hashCode();
    }
}
