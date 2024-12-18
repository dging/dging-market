package com.dging.dgingmarket.util;

import com.dging.dgingmarket.domain.user.User;
import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.exception.business.CEntityNotFoundException.CUserNotFoundException;

public class EntityUtils {
    public static User userThrowable() {
        return SecurityUtils.user()
                .orElseThrow(CUserNotFoundException::new);
    }
    public static User userThrowable(UserRepository userRepository, String seq) {
        return userRepository.findBySeq(seq)
                .orElseThrow(CUserNotFoundException::new);
    }
}
