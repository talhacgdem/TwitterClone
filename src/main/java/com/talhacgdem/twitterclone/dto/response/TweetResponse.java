package com.talhacgdem.twitterclone.dto.response;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
public class TweetResponse {
    private Long id;
    private String text;
    private LocalDateTime time;
    private List<UserFavResponseDto> favs;
}
