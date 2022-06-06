package com.example.task4.service;

import com.example.task4.entity.User;
import com.example.task4.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepo userRepo;

    public List<User> getUsers() {
        return userRepo.findAll();
    }

    public void deleteAllUsersById(List<Long> idList) {
        userRepo.deleteAllById(idList);
    }

    public void blockUsers(List<Long> idList) {
        List<User> users = userRepo.findAllById(idList);
        for (User user : users) {
            user.setActive(false);
            userRepo.save(user);
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
