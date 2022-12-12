package com.andrew2dos.restingplace.service;

import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public List<User> getAllUsers();
    public User getById(Long userId);
    public Optional<User> getByUserName(String userName);
    public boolean existsById(Long id);
    public boolean existsByUserName(String userName);
    public User save(User user);



}
