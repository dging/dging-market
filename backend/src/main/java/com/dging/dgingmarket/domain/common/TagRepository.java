package com.dging.dgingmarket.domain.common;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {

    List<Tag> findByNameIn(List<String> names);
}