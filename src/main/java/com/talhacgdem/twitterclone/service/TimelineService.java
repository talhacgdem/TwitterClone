package com.talhacgdem.twitterclone.service;

import com.talhacgdem.twitterclone.dto.response.TweetResponse;
import com.talhacgdem.twitterclone.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimelineService {
    private final UserService userService;
    private final TweetService tweetService;

    public List<TweetResponse> get(){
        List<User> followings = userService.getFollowingListFromActiveUser();
        return tweetService.getTimelineTweets(followings);
    }
}
