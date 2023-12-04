package com.company.taskmanagementsystem.service;

import com.company.taskmanagementsystem.entity.User;
import com.company.taskmanagementsystem.exception.NotFoundException;
import com.company.taskmanagementsystem.mapper.UserMapper;
import com.company.taskmanagementsystem.model.request.RegisterRequest;
import com.company.taskmanagementsystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;


    private final PasswordEncoder encoder;

    public void add(RegisterRequest registerRequest) {
        User user = userMapper.toEntity(registerRequest);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        userRepository.save(user);
    }

    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found "));
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
