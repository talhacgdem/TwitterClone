package com.talhacgdem.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TweetNotFoundException extends RuntimeException{
    public TweetNotFoundException(Long id){
        super("Tweet not found from given username : " + id);
    }
}
