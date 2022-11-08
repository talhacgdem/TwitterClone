package com.talhacgdem.twitterclone.controller;

import com.talhacgdem.twitterclone.dto.request.LoginRequestDto;
import com.talhacgdem.twitterclone.dto.request.RegisterDto;
import com.talhacgdem.twitterclone.security.JwtTokenProvider;
import com.talhacgdem.twitterclone.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;


    @PostMapping("login")
    public String login(@RequestBody LoginRequestDto loginRequestDto){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginRequestDto.getUsername(), loginRequestDto.getPassword()
        );

        Authentication auth = authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "Bearer " + jwtTokenProvider.generateJwtToken(auth);
    }


    @PutMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto){
        if (authService.getOneUserByUsername(registerDto.getUsername()) != null)
            return new ResponseEntity<>("Username already taken!", HttpStatus.OK);
        return new ResponseEntity<>(authService.register(registerDto), HttpStatus.CREATED);
    }
}
