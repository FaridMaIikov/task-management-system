package com.company.taskmanagementsystem.security;

import com.company.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(
                userRepository
                        .findUserByEmail(username)
                        .orElseThrow(() ->  new UsernameNotFoundException("Username or password wrong"))
        );
    }
}
