package com.dging.dgingmarket.service;

import com.dging.dgingmarket.config.WithCustomMockUser;
import com.dging.dgingmarket.domain.common.enums.Role;
import com.dging.dgingmarket.domain.store.Follower;
import com.dging.dgingmarket.domain.store.FollowerRepository;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.domain.store.exception.FollowMyselfException;
import com.dging.dgingmarket.util.EntityUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

            given(userRepository.findById(toId)).willReturn(Optional.of(to));

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
        @DisplayName("본인을 팔로우하면 실패")
        public void FollowMyself_Fail() {

            //given
            User from = EntityUtils.userThrowable();
            Long sameUserId = from.getId();

            given(userRepository.findById(sameUserId)).willReturn(Optional.of(from));

            //when & then
            assertThrows(FollowMyselfException.class, () -> storeService.follow(sameUserId));

        }
    }

}
