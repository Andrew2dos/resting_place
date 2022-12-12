package com.andrew2dos.restingplace.facade.impl;

import com.andrew2dos.restingplace.mappers.UserMapper;
import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.entity.User;
import com.andrew2dos.restingplace.facade.UserFacade;
import com.andrew2dos.restingplace.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl(UserService userService) {
        this.userService = userService;
    }

    public List<UserDto> getAllUsers() {
        return userService.getAllUsers()
                .stream()
                .map(this::toDto)
                .filter(userDto -> !userDto.getUserName().equals("admin"))
                .collect(Collectors.toList());
    }

    public UserDto getById(Long userId) {
        User user = userService.getById(userId);
        UserDto userDto = toDto(user);
        return userDto;
    }

    public User save(UserDto userDto) {
        User user = toModel(userDto);
        return userService.save(user);
    }

    public boolean existsById(Long id){
        return userService.existsById(id);
    }
    public boolean existsByUserName(String userName){
        return userService.existsByUserName(userName);
    }

    public UserDto getByUserName(String userName){
        Optional<User> optional = userService.getByUserName(userName);
        User user = optional.get();
        return toDto(user);
    }

    private User toModel(UserDto userDto) {
        return UserMapper.INSTANCE.toModel(userDto);
    }

    private UserDto toDto(User user) {
        return UserMapper.INSTANCE.toDto(user);
    }
}
