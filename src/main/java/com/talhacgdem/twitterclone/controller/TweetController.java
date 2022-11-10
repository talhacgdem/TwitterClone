package com.talhacgdem.twitterclone.controller;

import com.talhacgdem.twitterclone.dto.request.NewTweetRequestDto;
import com.talhacgdem.twitterclone.dto.request.RetweetRequestDto;
import com.talhacgdem.twitterclone.dto.request.TweetFavUnfavRequestDto;
import com.talhacgdem.twitterclone.service.TweetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
public class TweetController {
    private final TweetService tweetService;

    @GetMapping("list")
    public ResponseEntity<?> list(){
        return new ResponseEntity<>(tweetService.list(), HttpStatus.OK);
    }

    @PutMapping("/new")
    public ResponseEntity<?> newTweet(@RequestBody NewTweetRequestDto newTweetRequestDto){
        return new ResponseEntity<>(tweetService.newTweet(newTweetRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/retweet")
    public ResponseEntity<?> retweet(@RequestBody RetweetRequestDto retweetRequestDto){
        return new ResponseEntity<>(tweetService.retweet(retweetRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{tweetid}")
    public ResponseEntity<?> get(@PathVariable Long tweetid){
        return new ResponseEntity<>(tweetService.get(tweetid), HttpStatus.OK);
    }

    @PutMapping("/fav")
    public ResponseEntity<?> fav(@RequestBody TweetFavUnfavRequestDto tweetFavUnfavRequestDto){
        return new ResponseEntity<>(tweetService.fav(tweetFavUnfavRequestDto.getTweetId()), HttpStatus.CREATED);
    }

    @DeleteMapping("/unfav")
    public ResponseEntity<?> unfav(@RequestBody TweetFavUnfavRequestDto tweetFavUnfavRequestDto){
        return new ResponseEntity<>(tweetService.unfav(tweetFavUnfavRequestDto.getTweetId()), HttpStatus.OK);
    }


}
