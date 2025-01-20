package com.dging.dgingmarket.web.socket.dto;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisChatRoomInfoRepository extends CrudRepository<RedisChatRoomInfo, Long> {
}
