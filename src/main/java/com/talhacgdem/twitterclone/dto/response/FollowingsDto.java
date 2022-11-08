package com.talhacgdem.twitterclone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowingsDto {
    private String username;
    private String firstName;
    private String lastName;
    private String mail;
}
