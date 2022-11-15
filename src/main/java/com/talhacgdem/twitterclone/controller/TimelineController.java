package com.talhacgdem.twitterclone.controller;

import com.talhacgdem.twitterclone.service.TimelineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("timeline")
@RequiredArgsConstructor
public class TimelineController {
    private final TimelineService timelineService;

    @GetMapping("get")
    public ResponseEntity<?> get(){
        return ResponseEntity.ok(timelineService.get());
    }

    @GetMapping("me")
    public ResponseEntity<?> getMyTweets(){
        return ResponseEntity.ok(timelineService.getMyTweets());
    }
}
