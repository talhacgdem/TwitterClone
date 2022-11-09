package com.talhacgdem.twitterclone.service;

import com.talhacgdem.twitterclone.entity.User;
import com.talhacgdem.twitterclone.exception.UserNotFoundException;
import com.talhacgdem.twitterclone.repository.UserRepository;
import com.talhacgdem.twitterclone.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return JwtUserDetails.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return JwtUserDetails.create(user);
    }
}
