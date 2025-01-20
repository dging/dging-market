package com.dging.dgingmarket.web.socket.dto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedisChatMessageRepository extends CrudRepository<RedisChatMessage, Long> {
    List<RedisChatMessage> findByRoomId(Long roomId);
}
