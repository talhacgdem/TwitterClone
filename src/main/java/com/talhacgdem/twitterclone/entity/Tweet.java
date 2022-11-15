package com.talhacgdem.twitterclone.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Tweet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private LocalDateTime time;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user_id;

    @OneToOne
    @JoinColumn(name = "mention_id")
    private Tweet mention;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> favs;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<User> retweets;

}
