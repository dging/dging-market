package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.domain.common.Image;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = {"image", "product"})
@Table(name = "TBL_PRODUCT_IMAGE")
public class ProductImage {

    @EmbeddedId
    private ProductImageId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "imageId")
    @JoinColumn(name = "image_id", nullable = false, insertable = false, updatable = false)
    private Image image;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId(value = "productId")
    @JoinColumn(name = "product_id", nullable = false, insertable = false, updatable = false)
    private Product product;

    private int priority;

    public static ProductImage create(Product product, Image image, int priority) {

        ProductImage productImage = new ProductImage();
        productImage.setImage(image);
        productImage.setProduct(product);
        productImage.setPriority(priority);

        ProductImageId id = new ProductImageId();
        id.setImageId(image.getId());
        id.setProductId(product.getId());
        productImage.setId(id);

        return productImage;
    }
}
