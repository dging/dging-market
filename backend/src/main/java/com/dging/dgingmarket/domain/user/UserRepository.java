package com.dging.dgingmarket.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findBySeq(String seq);
    Optional<User> findBySocialIdAndProvider(String snsId, String provider);
}