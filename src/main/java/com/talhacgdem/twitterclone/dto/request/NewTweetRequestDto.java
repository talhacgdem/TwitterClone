package com.talhacgdem.twitterclone.dto.request;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@Builder
public class NewTweetRequestDto {
    private String text;
    private Long mentionId;
}
