package com.talhacgdem.twitterclone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MailValueNotPresentException extends RuntimeException{
    public MailValueNotPresentException(){
        super("There is no mail address on your request");
    }
}
