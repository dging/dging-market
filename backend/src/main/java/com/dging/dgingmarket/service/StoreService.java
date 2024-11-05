package com.dging.dgingmarket.service;

import com.dging.dgingmarket.domain.store.Follower;
import com.dging.dgingmarket.domain.store.FollowerRepository;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.exception.business.CEntityNotFoundException;
import com.dging.dgingmarket.util.EntityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StoreService {

    private final UserRepository userRepository;
    private final FollowerRepository followerRepository;

    @Transactional
    public void follow(Long id) {

        User from = EntityUtils.userThrowable();
        User to = userRepository.findById(id).orElseThrow(CEntityNotFoundException.CUserNotFoundException::new);

        Follower follower = Follower.create(from, to);
        followerRepository.save(follower);
    }
}
