package com.talhacgdem.twitterclone.controller;

import com.talhacgdem.twitterclone.dto.request.FollowRequestDto;
import com.talhacgdem.twitterclone.dto.request.UserUpdateDto;
import com.talhacgdem.twitterclone.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("list")
    public ResponseEntity<?> userList(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("update")
    public ResponseEntity<?> updateInformations(@RequestBody UserUpdateDto userUpdateDto){
        return new ResponseEntity<>(userService.updateInformations(userUpdateDto), HttpStatus.CREATED);
    }

    @PostMapping("follow")
    public ResponseEntity<?> followUser(@RequestBody FollowRequestDto followRequestDto){
        return new ResponseEntity<>(userService.follow(followRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("unfollow")
    public ResponseEntity<?> unFollowUser(@RequestBody FollowRequestDto followRequestDto){
        return new ResponseEntity<>(userService.unFollow(followRequestDto), HttpStatus.OK);
    }



}
