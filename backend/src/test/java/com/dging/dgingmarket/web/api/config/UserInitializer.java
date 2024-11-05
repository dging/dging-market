package com.dging.dgingmarket.web.api.config;

import com.dging.dgingmarket.domain.common.enums.Role;
import com.dging.dgingmarket.domain.store.Store;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Order;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
//@LocalDummyDataInitialization
//@Order(1)
public class UserInitializer implements ApplicationRunner {

    private final UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {

        if (userRepository.count() > 0) {
            log.info("[User] 더미 데이터 존재");
        } else {

            List<User> users = new ArrayList<>();

            User DUMMY_USER = User.create("userId", "password", "username", List.of(Role.USER));
            User DUMMY_ADMIN = User.create("adminId", "password", "username", List.of(Role.USER));

            users.add(DUMMY_USER);
            users.add(DUMMY_ADMIN);

            userRepository.saveAll(users);
        }

    }
}
