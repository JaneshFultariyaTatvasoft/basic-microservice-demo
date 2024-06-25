package com.example.userservice.repository;

import com.example.userservice.entity.ResetPassword;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetPasswordRepository extends JpaRepository<ResetPassword,Long> {

    Optional<ResetPassword> findByEmail(String email);
    Optional<ResetPassword> findByToken(String token);
}
