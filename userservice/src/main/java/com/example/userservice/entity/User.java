package com.example.userservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean active = true;

    private boolean accountLockedFlag = false;

    @Column(name = "account_locked_date")
    private LocalDate accountLockedDate;

    @Column(name = "unsuccessful_login_attempts")
    private int unsuccessfulLoginAttempts;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "userId"))
    private Set<Role> roles = new HashSet<>();
}
