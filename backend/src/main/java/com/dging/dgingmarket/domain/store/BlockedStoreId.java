package com.dging.dgingmarket.domain.store;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BlockedStoreId implements Serializable {

    private Long storeId;
    private Long userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BlockedStoreId)) return false;
        BlockedStoreId that = (BlockedStoreId) o;
        return storeId.equals(that.storeId) && userId.equals(that.userId);
    }

    @Override
    public int hashCode() {
        return storeId.hashCode() + userId.hashCode();
    }
}
