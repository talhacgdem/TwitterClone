package com.talhacgdem.twitterclone.dto;

import lombok.Data;

@Data
public class JwtDto {

    private Integer id;
    private String username;
    private String password;
}
