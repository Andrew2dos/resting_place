package com.andrew2dos.restingplace.dto;

import com.andrew2dos.restingplace.entity.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserDto {

    private Long id;

    private String userName;

    private String password;

    private String email;

    private Set<Role> roles = new HashSet<>();

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
