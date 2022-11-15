package com.talhacgdem.twitterclone.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private final LocalDateTime regDate = LocalDateTime.now();
    @Column(unique = true, nullable = false)
    private String mail;
    private String biography;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "id")
    private List<Tweet> tweets;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> followings;

}
