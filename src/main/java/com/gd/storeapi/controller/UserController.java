package com.gd.storeapi.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
// @Validated
@RestController
@RequestMapping("/v1/user")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<String> register() {
        return ResponseEntity.ok("User has been registered");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login() {
        return ResponseEntity.ok("Login successful");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword() {
        return ResponseEntity.status(509).body("Not implemented");
    }
}