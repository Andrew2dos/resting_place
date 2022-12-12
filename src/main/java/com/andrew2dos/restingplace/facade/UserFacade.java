package com.andrew2dos.restingplace.facade;

import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserFacade {

    public List<UserDto> getAllUsers();
    public UserDto getById(Long userId);
    public boolean existsById(Long id);
    public boolean existsByUserName(String userName);
    public User save(UserDto userDto);
    public UserDto getByUserName(String userName);

}
