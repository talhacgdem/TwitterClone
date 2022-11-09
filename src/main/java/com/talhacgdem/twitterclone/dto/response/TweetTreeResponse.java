package com.talhacgdem.twitterclone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TweetTreeResponse {
    private String text;
    private LocalDateTime time;
    private List<TweetTreeResponse> mentions;
}
