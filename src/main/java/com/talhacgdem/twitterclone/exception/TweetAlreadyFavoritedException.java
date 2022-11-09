package com.talhacgdem.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class TweetAlreadyFavoritedException extends RuntimeException {
    public TweetAlreadyFavoritedException(Long tweetid) {
        super("Tweet which given id already favorited: " + tweetid);
    }
}
