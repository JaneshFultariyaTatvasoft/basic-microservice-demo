package com.example.userservice.controller;

import com.example.userservice.dto.ResetPasswordDto;
import com.example.userservice.dto.UserRequestDto;
import com.example.userservice.entity.ResetPassword;
import com.example.userservice.entity.User;
import com.example.userservice.repository.ResetPasswordRepository;
import com.example.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    private final ResetPasswordRepository resetPasswordRepository;

    @PostMapping(value = "/user")
    public ResponseEntity<?> save(@RequestBody User user) {
        Optional<User> userOpt = userRepository.findByEmail(user.getEmail());
        if(userOpt.isPresent()){
            return ResponseEntity.ok("User is already exists : " + user.getEmail());
        }
        return ResponseEntity.ok(userRepository.save(user));
    }

    @PostMapping(value = "/user/update")
    public ResponseEntity<User> update(@RequestBody User user) {
        return ResponseEntity.ok(userRepository.save(user));
    }


    @GetMapping(value = "/user/all")
    public ResponseEntity<?> findAllUser() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping(value = "/user")
    public ResponseEntity<?> findByUsername(@RequestParam String username) {
        return ResponseEntity.ok(userRepository.findByUsername(username));
    }

    @PostMapping(value = "/user/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody UserRequestDto dto) {
        Optional<User> userOptl = userRepository.findByEmail(dto.getEmail());
        if (userOptl.isPresent()) {
            Random random = new Random();
            String token = "";
            for (int i = 0; i < 4; i++) {
                token += random.nextInt(9);
            }
            ResetPassword resetPassword = ResetPassword.builder().email(dto.getEmail()).token(token).build();
            return ResponseEntity.ok(resetPasswordRepository.save(resetPassword));
        } else {
            return ResponseEntity.ok("User not found for this email : " + dto.getEmail());
        }

    }

    @PostMapping(value = "/user/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam String token, @RequestBody ResetPasswordDto dto) {
        Optional<ResetPassword> resetPasswordOptional = resetPasswordRepository.findByToken(token);
        if (resetPasswordOptional.isPresent()) {
            Instant createdAtInstance = resetPasswordOptional.get().getCreatedAt().toInstant();
            Instant currentInstance = new Date().toInstant();
            Duration duration = Duration.between(createdAtInstance, currentInstance);
            if (duration.toHours() < 2) {
                User user = userRepository.findByEmail(resetPasswordOptional.get().getEmail()).get();
                PasswordEncoder encoder = new BCryptPasswordEncoder();
                user.setPassword(encoder.encode(dto.getNewPassword()));
                userRepository.save(user);
                resetPasswordRepository.delete(resetPasswordOptional.get());
                return ResponseEntity.ok("Password changed successfully!!");
            } else {
                resetPasswordRepository.delete(resetPasswordOptional.get());
                return ResponseEntity.ok("Regenerate token because your token time expired");
            }
        } else {
            return ResponseEntity.ok("Regenerate token because your token is not valid");
        }

    }
}
