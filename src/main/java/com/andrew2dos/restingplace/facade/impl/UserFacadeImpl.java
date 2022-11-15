package com.andrew2dos.restingplace.facade.impl;

import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.entity.User;
import com.andrew2dos.restingplace.facade.UserFacade;
import com.andrew2dos.restingplace.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserFacadeImpl implements UserFacade {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserFacadeImpl(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public List<UserDto> getAllUsers() {
        return userService.getAllUsers().stream()
                .map(this::toDto)
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

    private User toModel(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }

    private UserDto toDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }
}
