package com.talhacgdem.twitterclone.dto.response;

import com.talhacgdem.twitterclone.entity.Tweet;
import lombok.Data;

import java.util.List;

@Data
public class TimelineResponseDto {
    private List<TweetDetailResponseDto> tweets;
    private List<TweetDetailResponseDto> retweets;
}
