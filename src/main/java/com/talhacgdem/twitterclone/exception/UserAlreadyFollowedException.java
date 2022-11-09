package com.talhacgdem.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class UserAlreadyFollowedException extends RuntimeException {
    public UserAlreadyFollowedException(Long userid) {
        super("User which given id already followed: " + userid);
    }
}
