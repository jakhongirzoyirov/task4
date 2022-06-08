package com.example.task4.listener.authentication;

import com.example.task4.entity.User;
import com.example.task4.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationSuccessEventHandler implements AuthenticationSuccessHandler {

    public static final Map<Long, List<HttpSession>> sessionMap = new HashMap<>();

    private final UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        if (authentication != null && authentication.isAuthenticated()) {
            User currentUser = (User) authentication.getPrincipal();
            currentUser.setLoggedAt(LocalDateTime.now());
            userRepo.save(currentUser);

            if (sessionMap.containsKey(currentUser.getId())) {
                List<HttpSession> oldSessions = sessionMap.get(currentUser.getId());
                oldSessions.add(request.getSession());
                sessionMap.replace(currentUser.getId(), oldSessions);
            } else {
                sessionMap.put(currentUser.getId(), Arrays.asList(request.getSession()));
            }

            response.sendRedirect("/");
        }
    }
}
