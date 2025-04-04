package com.CalorieAPI.mapper;

import com.CalorieAPI.dto.UserRequestDto;
import com.CalorieAPI.dto.UserResponseDto;
import com.CalorieAPI.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toEntity(UserRequestDto dto);
    UserResponseDto toDto(User entity);
}
