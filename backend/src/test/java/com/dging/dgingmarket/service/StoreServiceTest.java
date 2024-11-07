package com.dging.dgingmarket.service;

import com.dging.dgingmarket.config.WithCustomMockUser;
import com.dging.dgingmarket.enums.Role;
import com.dging.dgingmarket.domain.store.Follower;
import com.dging.dgingmarket.domain.store.FollowerRepository;
import com.dging.dgingmarket.domain.store.StoreRepository;
import com.dging.dgingmarket.domain.store.exception.AlreadyFollowedException;
import com.dging.dgingmarket.domain.store.exception.FollowMyselfException;
import com.dging.dgingmarket.domain.store.exception.FollowerNotFoundException;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.util.EntityUtils;
import com.dging.dgingmarket.web.api.dto.common.CommonCondition;
import com.dging.dgingmarket.web.api.dto.store.FollowersResponse;
import com.dging.dgingmarket.web.api.dto.store.FollowingsResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StoreServiceTest {

    @InjectMocks
    private StoreService storeService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private FollowerRepository followerRepository;

    @Nested
    @DisplayName("상점 팔로우")
    @Transactional
    @WithCustomMockUser
    class FollowTest {


        @Test
        @DisplayName("성공")
        public void Success() {

            //given
            Long toId = 2L;
            User from = EntityUtils.userThrowable();
            User to = User.create("userId2", "password", "username", List.of(Role.USER));
            ReflectionTestUtils.setField(to, "id", toId);

            given(userRepository.findByStoreId(toId)).willReturn(Optional.of(to));

            //when
            storeService.follow(toId);

            //then
            ArgumentCaptor<Follower> followerCaptor = ArgumentCaptor.forClass(Follower.class);
            verify(followerRepository, times(1)).save(followerCaptor.capture());

            Follower savedFollower = followerCaptor.getValue();

            assertThat(savedFollower.getFrom()).isEqualTo(from);
            assertThat(savedFollower.getTo()).isEqualTo(to);
        }

        @Test
        @DisplayName("이미 팔로우한 사용자를 팔로우하려 하면 실패")
        public void AlreadyFollowed_Fail() {

            //given
            Long toId = 2L;
            User from = EntityUtils.userThrowable();
            User to = User.create("userId2", "password", "username", List.of(Role.USER));
            ReflectionTestUtils.setField(to, "id", toId);

            Follower follower = Follower.create(from, to);

            given(userRepository.findByStoreId(toId)).willReturn(Optional.of(to));
            given(followerRepository.findByFromAndTo(from, to)).willReturn(Optional.of(follower));

            //when & then
            assertThrows(AlreadyFollowedException.class, () -> storeService.follow(toId));

        }

        @Test
        @DisplayName("본인을 팔로우하려 하면 실패")
        public void FollowMyself_Fail() {

            //given
            User from = EntityUtils.userThrowable();
            Long sameUserId = from.getId();

            given(userRepository.findByStoreId(sameUserId)).willReturn(Optional.of(from));

            //when & then
            assertThrows(FollowMyselfException.class, () -> storeService.follow(sameUserId));

        }
    }

    @Nested
    @DisplayName("상점 언팔로우")
    @Transactional
    @WithCustomMockUser
    class UnfollowTest {


        @Test
        @DisplayName("성공")
        public void Success() {

            // given
            Long toId = 2L;
            User from = EntityUtils.userThrowable();
            User to = User.create("userId2", "password", "username", List.of(Role.USER));
            ReflectionTestUtils.setField(to, "id", toId);

            Follower follower = Follower.create(from, to);

            given(userRepository.findByStoreId(toId)).willReturn(Optional.of(to));
            given(followerRepository.findByFromAndTo(from, to)).willReturn(Optional.of(follower));

            // when
            storeService.unfollow(toId);

            // then
            verify(followerRepository, times(1)).delete(follower);
        }

        @Test
        @DisplayName("팔로우하지 않은 상점을 팔로우하려 하면 실패")
        public void Fail_FollowUnfollowedStore() {

            // given
            Long toId = 2L;
            User to = User.create("userId2", "password", "username", List.of(Role.USER));

            given(userRepository.findByStoreId(toId)).willReturn(Optional.of(to));

            // when & then
            assertThrows(FollowerNotFoundException.class, () -> storeService.unfollow(toId));
        }
    }

    @Nested
    @DisplayName("팔로워 조회")
    @Transactional
    @WithCustomMockUser
    class FollowersTest {

        @Test
        @DisplayName("성공")
        public void Success() {

            // given
            Long storeId = 1L;
            Pageable pageable = Pageable.unpaged();
            CommonCondition cond = new CommonCondition();
            Page<FollowersResponse> page = new PageImpl<>(new ArrayList<>());

            given(followerRepository.followers(pageable, storeId, cond)).willReturn(page);

            // when
            Page<FollowersResponse> result = storeService.followers(pageable, storeId, cond);

            // then
            assertEquals(page, result);
        }
    }

    @Nested
    @DisplayName("팔로잉 조회")
    @Transactional
    @WithCustomMockUser
    class FollowingsTest {

        @Test
        @DisplayName("성공")
        public void Success() {

            // given
            Long storeId = 1L;
            Pageable pageable = Pageable.unpaged();
            CommonCondition cond = new CommonCondition();
            Page<FollowingsResponse> page = new PageImpl<>(new ArrayList<>());

            given(followerRepository.followings(pageable, storeId, cond)).willReturn(page);

            // when
            Page<FollowingsResponse> result = storeService.followings(pageable, storeId, cond);

            // then
            assertEquals(page, result);
        }
    }

}
