package com.dging.dgingmarket.domain.store;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreQueryRepository {

    Optional<Store> findByName(String name);
}