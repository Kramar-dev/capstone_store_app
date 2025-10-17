package com.gd.storeapi.controller;

import com.gd.storeapi.dto.ErrorResponse;
import com.gd.storeapi.dto.UserLoginResponse;
import com.gd.storeapi.dto.UserRegisterResponse;
import com.gd.storeapi.service.JwtService;
import com.gd.storeapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
// @Validated
@RestController
@RequestMapping("/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<UserRegisterResponse> register(@RequestParam String email, @RequestParam String password) {
        UserRegisterResponse response = userService.register(email, password);
        String error = response.getMessage();
        if (error == null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @PostMapping(path = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<UserLoginResponse> login(@RequestParam String email, @RequestParam String password) {
        Optional<UserLoginResponse> userLoginResponse = userService.login(email, password);
        if (userLoginResponse.isPresent()) {
            UserLoginResponse response = userLoginResponse.get();
            if (response.getMessage() == null) {
                return ResponseEntity.ok().body(response);
            }
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping(path = "/reset-password", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> resetPassword() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}