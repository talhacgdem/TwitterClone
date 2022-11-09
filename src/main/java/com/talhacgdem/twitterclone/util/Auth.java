package com.talhacgdem.twitterclone.util;

import com.talhacgdem.twitterclone.dto.JwtDto;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Auth {

    private final ModelMapper modelMapper;

    private JwtDto parseSecurityContext(){
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        return modelMapper.map(
                a.getPrincipal(),
                JwtDto.class
        );
    }
    public String getAuthUsername(){
        return parseSecurityContext().getUsername();
    }

    public Long getAuthId(){
        return parseSecurityContext().getId();
    }
}
