package com.dging.dgingmarket.domain.store;

import com.dging.dgingmarket.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower, Long> {

    Optional<Follower> findByFromAndTo(User from, User to);
}