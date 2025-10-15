package com.gd.storeapi.controller;

import com.gd.storeapi.dto.UserDto;
import com.gd.storeapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
// @Validated
@RestController
@RequestMapping("/v1/user")
public class UserController {

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register() {
        userService.register(new UserDto());
        return ResponseEntity.ok("User has been registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        userService.login(new UserDto());
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword() {
        userService.resetPassword();
        return ResponseEntity.status(509).body("Not implemented");
    }
}