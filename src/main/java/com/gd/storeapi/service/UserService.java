package com.gd.storeapi.service;

import com.gd.storeapi.dto.UserLoginResponse;
import com.gd.storeapi.dto.UserRegisterResponse;
import com.gd.storeapi.model.Cart;
import com.gd.storeapi.model.User;
import com.gd.storeapi.repository.CartRepository;
import com.gd.storeapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class UserService {

    @Autowired
    private JwtService jwtService;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    public UserService(UserRepository userRepository, CartRepository cartRepository) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
    }

    @Transactional
    public UserRegisterResponse register(String email, String password) {
        UserRegisterResponse response = new UserRegisterResponse();
        try {
            User user = new User();
            user.setId(UUID.randomUUID().toString());
            user.setEmail(email);
            user.setPassword(password); // TODO: hash password
            user = userRepository.save(user);
            createCartForUser(user);
            response.setId(user.getId());
        } catch (Exception e) {
            log.error("Error during user registration", e);
            response.setMessage(e.getMessage());
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
            userRepository.save(user);
            response.setToken(user.getToken());
            response.setExpiration(user.getExpiration());
            return Optional.of(response);
        } catch (Exception e) {
            log.error("Error during user login", e);
            response.setMessage(e.getMessage());
            return Optional.of(response);
        }
    }

    private void createCartForUser(User user) {
        Cart cart = new Cart();
        cart.setUser(user);      // owning side set
        user.setCart(cart);      // inverse side set for consistency
        cartRepository.save(cart);
    }
}
