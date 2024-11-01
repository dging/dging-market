package com.dging.dgingmarket.domain.common;

import com.dging.dgingmarket.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByIdIn(List<Long> ids);
}