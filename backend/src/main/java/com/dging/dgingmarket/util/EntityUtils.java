package com.dging.dgingmarket.util;

import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.domain.user.exception.UserNotFoundException;

public class EntityUtils {
    public static User userThrowable() {
        return SecurityUtils.user()
                .orElseThrow(UserNotFoundException::new);
    }
    public static User userThrowable(UserRepository userRepository, Long id) {
        return userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);
    }
}
