package com.talhacgdem.twitterclone.controller;

import com.talhacgdem.twitterclone.dto.request.NewTweetRequestDto;
import com.talhacgdem.twitterclone.dto.request.RetweetRequestDto;
import com.talhacgdem.twitterclone.dto.request.TweetFavUnfavRequestDto;
import com.talhacgdem.twitterclone.dto.response.TweetResponse;
import com.talhacgdem.twitterclone.dto.response.TweetTreeResponse;
import com.talhacgdem.twitterclone.service.TweetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
@RequiredArgsConstructor
@Api(value = "Tweet Api documentation")
public class TweetController {
    private final TweetService tweetService;

    @GetMapping("list")
    public ResponseEntity<List<TweetResponse>> list(){
        return new ResponseEntity<>(tweetService.list(), HttpStatus.OK);
    }

    @PutMapping("/new")
    @ApiOperation(value = "Create a new tweet")
    public ResponseEntity<TweetResponse> newTweet(@RequestBody NewTweetRequestDto newTweetRequestDto){
        return new ResponseEntity<>(tweetService.newTweet(newTweetRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/retweet")
    @ApiOperation(value = "Retweet with any tweet id")
    public ResponseEntity<TweetResponse> retweet(@RequestBody RetweetRequestDto retweetRequestDto){
        return new ResponseEntity<>(tweetService.retweet(retweetRequestDto), HttpStatus.CREATED);
    }

    @GetMapping("/get/{tweetid}")
    @ApiOperation(value = "Get tweet details with given id")
    public ResponseEntity<TweetTreeResponse> get(@PathVariable Long tweetid){
        return new ResponseEntity<>(tweetService.get(tweetid), HttpStatus.OK);
    }

    @PutMapping("/fav")
    @ApiOperation(value = "Add favorite a tweet with given id")
    public ResponseEntity<TweetResponse> fav(@RequestBody TweetFavUnfavRequestDto tweetFavUnfavRequestDto){
        return new ResponseEntity<>(tweetService.fav(tweetFavUnfavRequestDto.getTweetId()), HttpStatus.CREATED);
    }

    @DeleteMapping("/unfav")
    @ApiOperation(value = "Remove favorite a tweet with given id")
    public ResponseEntity<TweetResponse> unfav(@RequestBody TweetFavUnfavRequestDto tweetFavUnfavRequestDto){
        return new ResponseEntity<>(tweetService.unfav(tweetFavUnfavRequestDto.getTweetId()), HttpStatus.OK);
    }



}
