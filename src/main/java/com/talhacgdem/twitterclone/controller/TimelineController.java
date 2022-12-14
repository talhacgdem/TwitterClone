package com.talhacgdem.twitterclone.controller;

import com.talhacgdem.twitterclone.dto.response.TimelineResponseDto;
import com.talhacgdem.twitterclone.dto.response.TweetResponse;
import com.talhacgdem.twitterclone.service.TimelineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("timeline")
@RequiredArgsConstructor
@Api(value = "Timeline Api documentation")
public class TimelineController {
    private final TimelineService timelineService;

    @GetMapping("get")
    @ApiOperation(value = "Get tweet from followed users")
    public ResponseEntity<TimelineResponseDto> get(){
        return ResponseEntity.ok(timelineService.get());
    }

    @GetMapping("/mine")
    @ApiOperation(value = "Get tweet from authorized user")
    public ResponseEntity<List<TweetResponse>> mine(){
        return new ResponseEntity<>(timelineService.getMyTweets(), HttpStatus.OK);
    }

}
