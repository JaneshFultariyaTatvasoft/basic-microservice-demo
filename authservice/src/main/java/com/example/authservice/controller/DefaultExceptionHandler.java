package com.example.authservice.controller;

import com.example.authservice.dto.User;
import com.example.authservice.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private UserRepository userRepository;

    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public ResponseEntity<?> handleAuthenticationException(HttpServletRequest request, AuthenticationException e) throws IOException {
        String username = (String)request.getAttribute("username");
        Optional<User> userOpt = userRepository.findByUsername(username);
        if(userOpt.isPresent()){
            User user = userOpt.get();
            if(user.isAccountLockedFlag()){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Your account is locked so you can contact admin to unlock your account");
            }
            if(user.getUnsuccessfulLoginAttempts() >= 3){
                user.setAccountLockedFlag(true);
                user.setAccountLockedDate(LocalDate.now());
                user.setUnsuccessfulLoginAttempts(0);
            }else{
                user.setUnsuccessfulLoginAttempts(user.getUnsuccessfulLoginAttempts()+1);
            }
            userRepository.update(user);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication Failed");
    }

}
