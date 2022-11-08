package com.talhacgdem.twitterclone.repository;

import com.talhacgdem.twitterclone.entity.Tweet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TweetRepository extends JpaRepository<Tweet, Long> {
}
