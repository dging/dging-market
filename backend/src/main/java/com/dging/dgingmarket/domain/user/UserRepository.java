package com.dging.dgingmarket.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findBySocialIdAndProvider(String snsId, String provider);
    Optional<User> findByUserId(String userId);
    @Query("SELECT u FROM User u INNER JOIN Store s ON s.user = u WHERE s.id = :storeId")
    Optional<User> findByStoreId(Long storeId);
}