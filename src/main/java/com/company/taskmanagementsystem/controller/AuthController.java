package com.company.taskmanagementsystem.controller;

import com.company.taskmanagementsystem.model.request.LoginRequest;
import com.company.taskmanagementsystem.model.request.RegisterRequest;
import com.company.taskmanagementsystem.model.request.VerifyRequest;
import com.company.taskmanagementsystem.model.response.LoginResponse;
import com.company.taskmanagementsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest){
        service.register(registerRequest);
        return new ResponseEntity<>("Verify code to email", HttpStatus.CREATED);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verify(@RequestBody VerifyRequest verifyRequest){
        service.verify(verifyRequest);
        return new ResponseEntity<>("Account successfully verification",HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(service.login(loginRequest));
    }

}
