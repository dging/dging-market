package com.dging.dgingmarket.service.security;

import com.dging.dgingmarket.domain.user.UserRepository;
import com.dging.dgingmarket.exception.business.CEntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
        return userRepository.findById(Integer.parseInt(userPk)).orElseThrow(CEntityNotFoundException.CUserNotFoundException::new);
    }
}
