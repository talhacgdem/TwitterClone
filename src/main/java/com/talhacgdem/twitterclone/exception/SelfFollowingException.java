package com.talhacgdem.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class SelfFollowingException extends RuntimeException{
    public SelfFollowingException(){
        super("Self-followings are impossible");
    }
}
