package com.talhacgdem.twitterclone.exception;

import com.sun.istack.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler extends ResponseEntityExceptionHandler {

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatus status,
                                                                  @NotNull WebRequest request){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(
                error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                }
        );

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MailValueNotPresentException.class)
    public ResponseEntity<?> mailValueNotPresentExceptionHandler(MailValueNotPresentException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UsernameValueNotPresentException.class)
    public ResponseEntity<?> usernameValueNotPresentExceptionHandler(UsernameValueNotPresentException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PasswordValueNotPresentException.class)
    public ResponseEntity<?> passwordValueNotPresentExceptionHandler(PasswordValueNotPresentException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SelfFollowingException.class)
    public ResponseEntity<?> selfFollowingExceptionHandler(SelfFollowingException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> userNotFoundExceptionHandler(UserNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TweetNotFoundException.class)
    public ResponseEntity<?> tweetNotFoundExceptionHandler(TweetNotFoundException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TweetAlreadyFavoritedException.class)
    public ResponseEntity<?> tweetAlreadyFavoritedExceptionHandler(TweetAlreadyFavoritedException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(TweetAlreadyUnfavoritedException.class)
    public ResponseEntity<?> tweetAlreadyUnfavoritedExceptionHandler(TweetAlreadyUnfavoritedException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(UserAlreadyFollowedException.class)
    public ResponseEntity<?> userAlreadyFollowedExceptionHandler(UserAlreadyFollowedException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(UserAlreadyUnfollowedException.class)
    public ResponseEntity<?> userAlreadyunFollowedExceptionHandler(UserAlreadyUnfollowedException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.ALREADY_REPORTED);
    }
}
