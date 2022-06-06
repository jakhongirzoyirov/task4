package com.example.task4.listener.authentication;

import com.example.task4.entity.User;
import com.example.task4.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AuthenticationEventHandler {

    private final UserRepo userRepo;

    @Autowired
    public AuthenticationEventHandler(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @EventListener
    public void authenticationSuccessEventListener(AuthenticationSuccessEvent authenticationSuccessEvent) {
        Authentication authentication = authenticationSuccessEvent.getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            User principal = (User) authentication.getPrincipal();
            principal.setLoggedAt(LocalDateTime.now());
            userRepo.save(principal);
        }
    }

    @EventListener
    public void authenticationFailureEventListener(AbstractAuthenticationFailureEvent abstractAuthenticationFailureEvent) {
        // todo write logic
    }
}
