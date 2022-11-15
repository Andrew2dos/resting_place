package com.andrew2dos.restingplace.service;

import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.entity.User;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public User getById(Long userId);
    public User save(User user);

}
