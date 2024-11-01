package com.dging.dgingmarket.domain.product;

import com.dging.dgingmarket.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {


}