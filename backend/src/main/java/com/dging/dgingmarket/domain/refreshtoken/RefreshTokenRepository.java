package com.dging.dgingmarket.domain.refreshtoken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByKey(Long key);

    @Transactional
    void deleteByKey(Long key);
}
