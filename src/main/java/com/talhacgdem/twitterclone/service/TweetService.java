package com.talhacgdem.twitterclone.service;

import com.talhacgdem.twitterclone.dto.request.NewTweetRequestDto;
import com.talhacgdem.twitterclone.dto.request.RetweetRequestDto;
import com.talhacgdem.twitterclone.dto.response.TweetResponse;
import com.talhacgdem.twitterclone.dto.response.TweetTreeResponse;
import com.talhacgdem.twitterclone.entity.Tweet;
import com.talhacgdem.twitterclone.entity.User;
import com.talhacgdem.twitterclone.exception.TweetAlreadyFavoritedException;
import com.talhacgdem.twitterclone.exception.TweetAlreadyUnfavoritedException;
import com.talhacgdem.twitterclone.exception.TweetNotFoundException;
import com.talhacgdem.twitterclone.repository.TweetRepository;
import com.talhacgdem.twitterclone.util.Auth;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TweetService {
    private final TweetRepository tweetRepository;
    private final ModelMapper modelMapper;
    private final Auth auth;
    private final UserService userService;

    public TweetResponse newTweet(NewTweetRequestDto newTweetRequestDto) {
        User activeUser = userService.findById(auth.getAuthId());
        Tweet tweet = new Tweet();
        tweet.setText(newTweetRequestDto.getText());
        tweet.setTime(LocalDateTime.now());
        tweet.setUser_id(activeUser);

        Long mentionId = newTweetRequestDto.getMentionId();
        tweet.setMention(
                mentionId == null ? null :
                        tweetRepository.findById(mentionId)
                                .orElseThrow(() -> new TweetNotFoundException(mentionId)));


        return modelMapper.map(tweetRepository.save(tweet), TweetResponse.class);
    }

    public List<TweetResponse> list() {
        return tweetRepository.findAll().stream().map(t -> modelMapper.map(t, TweetResponse.class))
                .collect(Collectors.toList());
    }

    public TweetResponse retweet(RetweetRequestDto retweetRequestDto) {
        Tweet t = tweetRepository.findById(retweetRequestDto.getTweetId())
                .orElseThrow(() -> new TweetNotFoundException(retweetRequestDto.getTweetId()));

        t = userService.retweet(t);
        return modelMapper.map(tweetRepository.save(t), TweetResponse.class);
    }

    public TweetTreeResponse get(Long tweetid) {

        Tweet tweet = tweetRepository.findById(tweetid)
                .orElseThrow(() -> new TweetNotFoundException(tweetid));

        List<TweetTreeResponse> mentions = findMentions(tweet);

        return TweetTreeResponse.builder()
                .text(tweet.getText())
                .time(tweet.getTime())
                .mentions(mentions)
                .build();
    }
    private List<TweetTreeResponse> findMentions(Tweet root) {
        return tweetRepository.findByMention(root).stream()
                .map(
                        m -> TweetTreeResponse.builder()
                                .time(m.getTime())
                                .text(m.getText())
                                .mentions(findMentions(m))
                                .build()
                )
                .collect(Collectors.toList());
    }
    public TweetResponse fav(Long tweetid) {
        User activeUser = userService.findById(auth.getAuthId());
        Tweet tweet = tweetRepository.findById(tweetid).orElseThrow(() -> new TweetNotFoundException(tweetid));
        List<User> favs = tweet.getFavs();
        if (favs.contains(activeUser))
            throw new TweetAlreadyFavoritedException(tweetid);
        favs.add(activeUser);
        tweet.setFavs(favs);
        return modelMapper.map(tweetRepository.save(tweet), TweetResponse.class);
    }
    public TweetResponse unfav(Long tweetid) {
        User activeUser = userService.findById(auth.getAuthId());
        Tweet tweet = tweetRepository.findById(tweetid).orElseThrow(() -> new TweetNotFoundException(tweetid));
        List<User> favs = tweet.getFavs();
        if (!favs.contains(activeUser))
            throw new TweetAlreadyUnfavoritedException(tweetid);
        favs.remove(activeUser);
        tweet.setFavs(favs);
        return modelMapper.map(tweetRepository.save(tweet), TweetResponse.class);
    }

    protected List<TweetResponse> getTimelineTweets(List<User> users){
        List<TweetResponse> tweets = tweetRepository.findByUser_idInOrderByTimeDesc(users).stream()
                .map(tr -> modelMapper.map(tr, TweetResponse.class)).toList();



        List<TweetResponse> retweets = tweetRepository.findByRetweetsInOrderByTimeDesc(users).stream()
                .map(tr -> modelMapper.map(tr, TweetResponse.class)).toList();
        return retweets;
    }


    public List<TweetResponse> getTweetsFromUser(User activeUser) {
        return tweetRepository.findByUser_id(activeUser).stream().map(
                t -> modelMapper.map(t, TweetResponse.class)
        ).collect(Collectors.toList());
    }
}
