package com.talhacgdem.twitterclone.service;

import com.talhacgdem.twitterclone.dto.request.RegisterDto;
import com.talhacgdem.twitterclone.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;


    public User getOneUserByUsername(String username) {
        return userService.getOneUserByUsername(username);
    }

    public User register(RegisterDto registerDto) {
        return userService.register(registerDto);
    }
}
