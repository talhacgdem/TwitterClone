package com.talhacgdem.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class TweetAlreadyUnfavoritedException extends RuntimeException {
    public TweetAlreadyUnfavoritedException(Long tweetid) {
        super("Tweet which given id already un-favorited: " + tweetid);
    }
}
