package com.company.taskmanagementsystem.service;

import com.company.taskmanagementsystem.entity.User;
import com.company.taskmanagementsystem.exception.AlreadyExistsException;
import com.company.taskmanagementsystem.model.request.LoginRequest;
import com.company.taskmanagementsystem.model.request.RegisterRequest;
import com.company.taskmanagementsystem.model.request.VerifyRequest;
import com.company.taskmanagementsystem.model.response.LoginResponse;
import com.company.taskmanagementsystem.model.response.RefreshTokenDto;
import com.company.taskmanagementsystem.repository.UserRepository;
import com.company.taskmanagementsystem.security.AccessTokenManager;
import com.company.taskmanagementsystem.security.RefreshTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccessTokenManager accessTokenManager;
    private final RefreshTokenManager refreshTokenManager;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRepository userRepository;
    private final VerifierService verifierService;
    private final EmailService emailService;

    public void register(RegisterRequest registerRequest) {

        if (userService.existsByEmail(registerRequest.getEmail())) {
            throw new AlreadyExistsException("This email is already");
        }

        userService.add(registerRequest);

        String otp = verifierService.generateOTP(6);
        emailService.sendEmail(registerRequest.getEmail(),"OTP",otp);
    }

    public void verify(VerifyRequest verifyRequest) {
        User user = userService.getByEmail(verifyRequest.getEmail());
        verifierService.add(verifyRequest.getEmail(),verifyRequest.getCode());
        user.setActive(true);
        userRepository.save(user);
    }
    public LoginResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));

        User user = userService.getByEmail(request.email());

        var accessToken = accessTokenManager.generate(user);

        var refreshToken = refreshTokenManager
                .generate(RefreshTokenDto
                        .builder()
                        .user(user)
                        .rememberMe(request.rememberMe())
                        .build());

        return new LoginResponse(accessToken, refreshToken);
    }
}

