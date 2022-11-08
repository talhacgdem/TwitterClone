package com.talhacgdem.twitterclone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String mail;
    private String biography;
}
