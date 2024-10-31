package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.domain.common.Tag;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_PRODUCT_TAG")
@IdClass(ProductTagId.class)
public class ProductTag {

    @Id
    @Column(name = "product_id")
    private Long productId;

    @Id
    @Column(name = "tag_id")
    private Long tagId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id", nullable = false, insertable = false, updatable = false)
    private Tag tag;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductTag that = (ProductTag) o;
        return Objects.equals(product, that.product) && Objects.equals(tag.getName(), that.tag.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, tag);
    }

    public static ProductTag create(Product product, Tag tag) {
        ProductTag productTag = new ProductTag();
        productTag.setProduct(product);
        productTag.setTag(tag);
        return productTag;
    }
}