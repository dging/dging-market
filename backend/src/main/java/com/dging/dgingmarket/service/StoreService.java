package com.dging.dgingmarket.service;

import com.dging.dgingmarket.domain.store.Follower;
import com.dging.dgingmarket.domain.store.FollowerRepository;
import com.dging.dgingmarket.domain.store.exception.AlreadyFollowedException;
import com.dging.dgingmarket.domain.store.exception.FollowerNotFoundException;
import com.dging.dgingmarket.domain.store.exception.StoreNotFoundException;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.domain.store.exception.FollowMyselfException;
import com.dging.dgingmarket.domain.user.exception.UserNotFoundException;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.store.FollowersResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowingsResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        User to = userRepository.findByStoreId(id).orElseThrow(StoreNotFoundException::new);

        followerRepository.findByFromAndTo(from, to).ifPresent(follower -> {
            throw AlreadyFollowedException.EXCEPTION;
        });

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
        User to = userRepository.findByStoreId(id).orElseThrow(StoreNotFoundException::new);

        Follower follower = followerRepository.findByFromAndTo(from, to).orElseThrow(FollowerNotFoundException::new);

        followerRepository.delete(follower);
    }

    public Page<FollowersResponse> followers(Pageable pageable, Long id, CommonCondition cond) {
        return followerRepository.followers(pageable, id, cond);
    }

    public Page<FollowingsResponse> followings(Pageable pageable, Long id, CommonCondition cond) {
        return followerRepository.followings(pageable, id, cond);
    }
}
