package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.domain.common.Image;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter(value = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TBL_PRODUCT_IMAGE")
@DiscriminatorValue("상품")
public class ProductImage extends Image {
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    public static ProductImage create(String fileName, String path, String url, int size) {
        ProductImage productImage = new ProductImage();
        productImage.setFileName(fileName);
        productImage.setPath(path);
        productImage.setUrl(url);
        productImage.setSize(size);
        return productImage;
    }
}
