package com.gd.storeapi.repository;

import com.gd.storeapi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

/*    @Modifying
    @Transactional
    @Query(
            value = "INSERT INTO users (id, email, password) VALUES (:id, :email, :password)",
            nativeQuery = true
    )
    String register(@Param("id") String id, @Param("email") String email, @Param("password") String password);*/

    Optional<User> findByEmailAndPassword(String email, String password);
/*
    @Modifying
    @Transactional
    @Query(value = """
            UPDATE users
            SET token = :token,
                expiration = now() + CAST(:expirationInterval AS INTERVAL)
            WHERE email = :email AND password = :password
            """, nativeQuery = true)
    int login(@Param("email") String email,
              @Param("password") String password,
              @Param("token") String token,
              @Param("expirationInterval") String expirationInterval);*/

    @Query(value = "SELECT * FROM users WHERE email = :email", nativeQuery = true)
    User getAllByEmail(@Param("email") String email);

    Optional<User> findByEmail(String email);
}
