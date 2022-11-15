package com.talhacgdem.twitterclone.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class TweetDetailResponseDto {
    private Long id;
    private String text;
    private LocalDateTime time;
    private List<UserFavResponseDto> favs;
    private UserFavResponseDto user;
}
