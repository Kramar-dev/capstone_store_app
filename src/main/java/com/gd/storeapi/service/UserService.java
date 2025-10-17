package com.gd.storeapi.service;

import com.gd.storeapi.dto.UserDto;
import com.gd.storeapi.dto.UserLoginResponse;
import com.gd.storeapi.dto.UserRegisterResponse;
import com.gd.storeapi.mapper.UserMapper;
import com.gd.storeapi.model.User;
import com.gd.storeapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRegisterResponse register(String email, String password) {
        UserRegisterResponse response = new UserRegisterResponse();
        try {
            // hash password
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setEmail(email);
            user.setPassword(password);
            user = userRepository.save(user);
            response.setId(user.getId());
        } catch (Exception e) {
            log.error("Error during user registration", e);
            response.setMessage(e.getCause().getMessage());
        }
        return response;
    }

    public Optional<UserLoginResponse> login(String email, String password) {
        UserLoginResponse response = new UserLoginResponse();
        try {
            var userOpt = userRepository.findByEmailAndPassword(email, password);
            if (userOpt.isEmpty()) {
                response.setMessage("Invalid credentials");
                return Optional.of(response);
            }
            User user = userOpt.get();
            String token = jwtService.generateToken(user.getId());
            user.setToken(token);
            user.setExpiration(Instant.now().plus(30, ChronoUnit.DAYS).toString());
            userRepository.save(user); // managed flush
            response.setToken(user.getToken());
            response.setExpiration(user.getExpiration());
            return Optional.of(response);
        } catch (Exception e) {
            log.error("Error during user login", e);
            response.setMessage(e.getMessage());
            return Optional.of(response);
        }
    }
}
