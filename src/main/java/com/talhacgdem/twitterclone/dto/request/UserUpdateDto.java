package com.talhacgdem.twitterclone.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserUpdateDto {
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String biography;
}
