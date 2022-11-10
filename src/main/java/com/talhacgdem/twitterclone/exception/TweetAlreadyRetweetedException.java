package com.talhacgdem.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class TweetAlreadyRetweetedException extends RuntimeException {
    public TweetAlreadyRetweetedException(Long tweetid) {
        super("Tweet which given id already re-tweeted: " + tweetid);
    }
}
