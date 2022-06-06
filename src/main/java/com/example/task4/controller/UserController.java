package com.example.task4.controller;

import com.example.task4.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/deleteUsers")
    public ResponseEntity<?> deleteUsers(@RequestBody List<Long> idList) {
        userService.deleteAllUsersById(idList);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/blockUsers")
    public ResponseEntity<?> blockUsers(@RequestBody List<Long> idList) {
        userService.blockUsers(idList);
        return ResponseEntity.ok(null);
    }

    @PostMapping("/unblockUsers")
    public ResponseEntity<?> unblockUsers(@RequestBody List<Long> idList) {
        userService.unblockUsers(idList);
        return ResponseEntity.ok(null);
    }
}
