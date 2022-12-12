package com.andrew2dos.restingplace.mappers;

import com.andrew2dos.restingplace.dto.UserDto;
import com.andrew2dos.restingplace.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toModel(UserDto userDto);
    UserDto toDto(User user);
}
