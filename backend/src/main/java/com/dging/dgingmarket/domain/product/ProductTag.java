package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.domain.common.Tag;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = {"id"})
@Table(name = "TBL_PRODUCT_TAG")
public class ProductTag {

    @EmbeddedId
    private ProductTagId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("tagId")
    @JoinColumn(name = "tag_id", insertable = false, updatable = false)
    private Tag tag;

    private int priority;

    public static ProductTag create(Product product, Tag tag, int priority) {

        tag.used();

        ProductTag productTag = new ProductTag();
        productTag.setProduct(product);
        productTag.setTag(tag);
        productTag.setPriority(priority);

        ProductTagId id = new ProductTagId();
        id.setProductId(product.getId());
        id.setTagId(tag.getId());
        productTag.setId(id);

        return productTag;
    }
}