package com.dging.dgingmarket.service;

import com.dging.dgingmarket.domain.store.Follower;
import com.dging.dgingmarket.domain.store.FollowerRepository;
import com.dging.dgingmarket.domain.store.exception.FollowerNotFoundException;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.domain.store.exception.FollowMyselfException;
import com.dging.dgingmarket.domain.user.exception.UserNotFoundException;
import com.dging.dgingmarket.util.EntityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

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
        User to = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        //본인을 팔로우 하는 경우 예외
        if(Objects.equals(from.getId(), to.getId())) {
            throw FollowMyselfException.EXCEPTION;
        }

        Follower follower = Follower.create(from, to);
        followerRepository.save(follower);
    }

    @Transactional
    public void unfollow(Long id) {

        User from = EntityUtils.userThrowable();
        User to = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        Follower follower = followerRepository.findByFromAndTo(from, to).orElseThrow(FollowerNotFoundException::new);

        followerRepository.delete(follower);
    }
}
