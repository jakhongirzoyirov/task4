package com.example.task4.service;

import com.example.task4.entity.User;
import com.example.task4.payload.RegisterDto;
import com.example.task4.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public boolean register(RegisterDto registerDto) {
        if (userRepo.existsByEmail(registerDto.getEmail())) {
            return false;
        }

        userRepo.save(new User(
                registerDto.getFirstName(),
                registerDto.getLastName(),
                registerDto.getEmail(),
                passwordEncoder.encode(registerDto.getPassword()),
                LocalDateTime.now(),
                true
        ));

        return true;
    }
}
