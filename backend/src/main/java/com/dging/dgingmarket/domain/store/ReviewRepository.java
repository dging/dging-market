package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long>, ReviewQueryRepository {

}