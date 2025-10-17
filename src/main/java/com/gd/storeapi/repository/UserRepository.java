package com.gd.storeapi.repository;

import com.gd.storeapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByEmailAndPassword(String email, String password);

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User getAllByEmail(@Param("email") String email);

    Optional<User> findByEmail(String email);
}
