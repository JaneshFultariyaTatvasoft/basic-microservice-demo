package com.example.authservice.repository;

import com.example.authservice.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "USER-SERVICE")
public interface UserRepository {

    @RequestMapping(method = RequestMethod.POST, value = "/api/user")
    ResponseEntity<?> save(@RequestBody User user);

    @RequestMapping(method = RequestMethod.POST, value = "/api/user/update")
    User update(@RequestBody User user);

    @RequestMapping(method = RequestMethod.GET, value = "/api/user")
    Optional<User> findByUsername(@RequestParam String username);
}
