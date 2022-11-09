package com.talhacgdem.twitterclone.dto;

import lombok.Data;

@Data
public class JwtDto {

    private Long id;
    private String username;
    private String password;
}
