package com.dging.dgingmarket.domain.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductQueryRepository {

    Optional<Product> findByIdAndDeletedIsFalse(Long id);
}