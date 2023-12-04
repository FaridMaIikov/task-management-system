package com.company.taskmanagementsystem.service;

import com.company.taskmanagementsystem.entity.Verifier;
import com.company.taskmanagementsystem.exception.NotFoundException;
import com.company.taskmanagementsystem.repository.VerifierRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@RequiredArgsConstructor
public class VerifierService {

    private final VerifierRepository verifierRepository;

    public void add(String email, String code) {
        Verifier verifier = Verifier.builder()
                .email(email)
                .code(code)
                .build();

        verifierRepository.save(verifier);
    }


    public String generateOTP(int length){
        if (!(length == 6)) {
            throw new IllegalArgumentException("Length should be 5");
        }

        SecureRandom secureRandom = new SecureRandom();

        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int digit = secureRandom.nextInt(10); 
            otp.append(digit);
        }

        return otp.toString();
    }
}
