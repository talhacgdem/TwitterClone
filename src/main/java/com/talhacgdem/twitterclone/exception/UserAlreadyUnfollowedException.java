package com.talhacgdem.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class UserAlreadyUnfollowedException extends RuntimeException {
    public UserAlreadyUnfollowedException(Long userid) {
        super("User which given id already un-followed: " + userid);
    }
}
