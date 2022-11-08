package com.talhacgdem.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UsernameValueNotPresentException extends RuntimeException{
    public UsernameValueNotPresentException(){
        super("There is no username on your request");
    }
}
