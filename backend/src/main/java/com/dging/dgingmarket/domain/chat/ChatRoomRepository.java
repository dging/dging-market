package com.dging.dgingmarket.domain.chat;

import com.dging.dgingmarket.domain.product.Product;
import com.dging.dgingmarket.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByFromAndToAndProduct(User from, User to, Product product);
    List<ChatRoom> findByFromOrTo(User from, User to);
}
