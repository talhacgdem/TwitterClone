package com.talhacgdem.twitterclone.dto.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class RegisterDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String mail;
}
