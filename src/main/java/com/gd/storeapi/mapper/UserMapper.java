package com.gd.storeapi.mapper;

import com.gd.storeapi.dto.UserDto;
import com.gd.storeapi.model.User;

public class UserMapper {

    private UserMapper() {
    }

    public static UserDto toDto(User userEntity) {
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        return userDto;
    }

    public static User toEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }
}