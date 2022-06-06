package com.example.task4.service;

import com.example.task4.entity.User;
import com.example.task4.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public void deleteAllUsersById(List<Long> idList, HttpServletRequest request) {
        List<User> users = userRepo.findAllById(idList);

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for (User user : users) {
            if (user.getId().equals(currentUser.getId())) {
                SecurityContextHolder.clearContext();
                request.getSession(false).invalidate();
            }

            userRepo.delete(user);
        }
    }

    public void blockUsers(List<Long> idList, HttpServletRequest request) {
        List<User> users = userRepo.findAllById(idList);

        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        for (User user : users) {
            user.setActive(false);
            userRepo.save(user);

            if (user.getId().equals(currentUser.getId())) {
                SecurityContextHolder.clearContext();
                request.getSession(false).invalidate();
            }
        }
    }

    public void unblockUsers(List<Long> idList) {
        List<User> users = userRepo.findAllById(idList);
        for (User user : users) {
            user.setActive(true);
            userRepo.save(user);
        }
    }
}
