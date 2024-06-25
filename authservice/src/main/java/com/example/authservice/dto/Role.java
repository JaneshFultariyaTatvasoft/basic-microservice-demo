package com.example.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private long id;

    private String roleName;

    private boolean active;

    Set<Permission> permissions = new HashSet<>();
}
