package com.dging.dgingmarket.web.api.config;

import com.dging.dgingmarket.enums.Role;
import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
//@LocalDummyDataInitialization
//@Order(1)
public class UserInitializer implements ApplicationRunner {

    private UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) {

        if (userRepository.count() > 0) {

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
