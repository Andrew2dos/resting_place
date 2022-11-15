package com.andrew2dos.restingplace.dto;

import lombok.Data;

@Data
public class UserDto {

    private Long id;

    private String userName;

    private String password;

    private String email;

}
