package com.dging.dgingmarket.domain;

import com.dging.dgingmarket.domain.product.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {

    @Query("SELECT pi FROM ProductImage pi JOIN Image i on i = pi.image WHERE pi in :ids")
    List<ProductImage> findByIdIn(List<Long> ids);
}