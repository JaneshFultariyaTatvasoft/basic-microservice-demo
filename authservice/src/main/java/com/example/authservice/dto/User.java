package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private long id;

    private String username;

    private String email;

    private String password;

    private boolean active;

    private boolean accountLockedFlag;

    private LocalDate accountLockedDate;

    private int unsuccessfulLoginAttempts;

    private Set<Role> roles = new HashSet<>();
}
