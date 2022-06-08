package com.example.task4.listener;

import com.example.task4.entity.User;
import com.example.task4.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationEventHandler {

    private final UserRepo userRepo;

    @EventListener
    public void authenticationSuccessEventListener(AuthenticationSuccessEvent authenticationSuccessEvent) {
        Authentication authentication = authenticationSuccessEvent.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User currentUser = (User) authentication.getPrincipal();
            currentUser.setLoggedAt(LocalDateTime.now());
            userRepo.save(currentUser);
        }
    }
}
