package com.talhacgdem.twitterclone.service;

import com.talhacgdem.twitterclone.dto.request.FollowRequestDto;
import com.talhacgdem.twitterclone.dto.request.UserUpdateDto;
import com.talhacgdem.twitterclone.dto.response.FollowingsDto;
import com.talhacgdem.twitterclone.dto.response.TweetResponse;
import com.talhacgdem.twitterclone.dto.response.UserResponseDto;
import com.talhacgdem.twitterclone.entity.Tweet;
import com.talhacgdem.twitterclone.entity.User;
import com.talhacgdem.twitterclone.exception.*;
import com.talhacgdem.twitterclone.dto.mapper.UserMapper;
import com.talhacgdem.twitterclone.dto.request.RegisterDto;
import com.talhacgdem.twitterclone.repository.UserRepository;
import com.talhacgdem.twitterclone.util.Auth;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final Auth auth;



    public List<UserResponseDto> getAll() {
        return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserResponseDto.class)).collect(Collectors.toList());
    }

    public User findById(Long userId){
        return userRepository.findById(userId).orElseThrow(()->new UserNotFoundException(userId));
    }

    public List<TweetResponse> retweet(Tweet retweet) {
        User activeUser = getActiveUser();
        List<Tweet> retweets = activeUser.getRetweets();

        if (retweets.contains(retweet))
            retweets.remove(retweet);
        else
            retweets.add(retweet);

        activeUser.setRetweets(retweets);
        User u = userRepository.save(activeUser);
        return u.getRetweets().stream().map(rt -> modelMapper.map(rt, TweetResponse.class)).toList();
    }

    public User register(RegisterDto registerDto) {
        if (registerDto.getMail() == null || registerDto.getMail().equals("")) throw new MailValueNotPresentException();
        if (registerDto.getUsername() == null || registerDto.getUsername().equals(""))
            throw new UsernameValueNotPresentException();
        if (registerDto.getPassword() == null || registerDto.getPassword().equals(""))
            throw new PasswordValueNotPresentException();

        User u = userRepository.save(modelMapper.map(registerDto, User.class));
        u.setPassword(passwordEncoder.encode(u.getPassword()));
        return userRepository.save(u);
    }

    public UserResponseDto updateInformations(UserUpdateDto userUpdateDto) {
        Long userId = auth.getAuthId();
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        return modelMapper.map(
                userRepository.save(userMapper.convertFromUpdateDto(user, userUpdateDto)),
                UserResponseDto.class
        );
    }

    public List<FollowingsDto> follow(FollowRequestDto followRequestDto) {
        if(followRequestDto.getFollowId().equals(auth.getAuthId()))
            throw new SelfFollowingException();
        User target = getFollowProcessUser(followRequestDto.getFollowId());
        List<User> followings = getFollowingListFromActiveUser();
        if (followings.contains(target))
            throw new UserAlreadyFollowedException(target.getId());
        followings.add(target);
        User u = getActiveUser();
        u.setFollowings(followings);
        return userRepository.save(u).getFollowings().stream().map(
                f -> modelMapper.map(f, FollowingsDto.class)
        ).collect(Collectors.toList());
    }

    public List<FollowingsDto> unFollow(FollowRequestDto followRequestDto) {
        User activeUser = getActiveUser();
        User target = getFollowProcessUser(followRequestDto.getFollowId());
        List<User> followings = activeUser.getFollowings();
        if (!followings.contains(target))
            throw new UserAlreadyUnfollowedException(target.getId());
        followings.remove(target);
        activeUser.setFollowings(followings);
        return userRepository.save(activeUser).getFollowings().stream()
                .map(fl -> modelMapper.map(fl, FollowingsDto.class)).collect(Collectors.toList());
    }

    private User getFollowProcessUser(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
    }

    protected List<User> getFollowingListFromActiveUser() {
        return getActiveUser().getFollowings();
    }

    protected User getActiveUser(){
        Long authorId = auth.getAuthId();
        return userRepository.findById(authorId)
                .orElseThrow(() -> new UserNotFoundException(authorId));
    }

    public User getOneUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }



}
