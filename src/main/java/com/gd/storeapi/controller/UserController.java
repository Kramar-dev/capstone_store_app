package com.gd.storeapi.controller;

import com.gd.storeapi.dto.UserDto;
import com.gd.storeapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
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

    @PostMapping(path = "/register",  produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> register() {
        userService.register(new UserDto());
        return ResponseEntity.ok("User has been registered");
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> login() {
        userService.login(new UserDto());
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping(path = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> resetPassword() {
        userService.resetPassword();
        return ResponseEntity.status(501).body("Not implemented");
    }
}