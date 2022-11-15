package com.talhacgdem.twitterclone.repository;

import com.talhacgdem.twitterclone.dto.response.TweetResponse;
import com.talhacgdem.twitterclone.entity.Tweet;
import com.talhacgdem.twitterclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TweetRepository extends JpaRepository<Tweet, Long> {

    List<Tweet> findByMention(Tweet mention);

    @Query("select t from Tweet t where t.user_id in ?1 order by t.time DESC")
    List<Tweet> findByUser_idInOrderByTimeDesc(List<User> users);

    List<Tweet> findByUser_id(User activeUser);
}
