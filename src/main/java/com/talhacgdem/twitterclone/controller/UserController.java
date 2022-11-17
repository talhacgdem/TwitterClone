package com.talhacgdem.twitterclone.controller;

import com.talhacgdem.twitterclone.dto.request.FollowRequestDto;
import com.talhacgdem.twitterclone.dto.request.UserUpdateDto;
import com.talhacgdem.twitterclone.dto.response.FollowingsDto;
import com.talhacgdem.twitterclone.dto.response.UserResponseDto;
import com.talhacgdem.twitterclone.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
@Api(value = "User Api documentation")
public class UserController {
    private final UserService userService;

    @GetMapping("list")
    public ResponseEntity<List<UserResponseDto>> userList(){
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("update")
    @ApiOperation(value = "Update user informations")
    public ResponseEntity<UserResponseDto> updateInformations(@RequestBody UserUpdateDto userUpdateDto){
        return new ResponseEntity<>(userService.updateInformations(userUpdateDto), HttpStatus.CREATED);
    }

    @PostMapping("follow")
    @ApiOperation(value = "Follow an user with given id")
    public ResponseEntity<List<FollowingsDto>> followUser(@RequestBody FollowRequestDto followRequestDto){
        return new ResponseEntity<>(userService.follow(followRequestDto), HttpStatus.CREATED);
    }

    @PostMapping("unfollow")
    @ApiOperation(value = "Unfollow an user with given id")
    public ResponseEntity<List<FollowingsDto>> unFollowUser(@RequestBody FollowRequestDto followRequestDto){
        return new ResponseEntity<>(userService.unFollow(followRequestDto), HttpStatus.OK);
    }



}
