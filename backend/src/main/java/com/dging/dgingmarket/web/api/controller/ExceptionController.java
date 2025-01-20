package com.dging.dgingmarket.web.api.controller;

import com.dging.dgingmarket.exception.AccessDeniedException;
import com.dging.dgingmarket.exception.AuthenticationEntryPointException;
import com.dging.dgingmarket.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/exception")
@RequiredArgsConstructor
public class ExceptionController {

    @RequestMapping("/entrypoint")
    public void entryPointException() throws Exception {
        throw AuthenticationEntryPointException.EXCEPTION;
    }

    @RequestMapping("/access-denied")
    public void accessDeniedException() {
        throw AccessDeniedException.EXCEPTION;
    }

    @RequestMapping(value = "/user-not-found")
    public void userNotFoundException() {
        throw UserNotFoundException.EXCEPTION;
    }
}
