package com.andrew2dos.restingplace.facade;

import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.entity.User;

import java.util.List;

public interface UserFacade {

    public List<UserDto> getAllUsers();
    public UserDto getById(Long userId);
    public User save(UserDto userDto);

}
