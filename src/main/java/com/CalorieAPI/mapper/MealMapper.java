package com.CalorieAPI.mapper;

import com.CalorieAPI.dto.MealRequestDto;
import com.CalorieAPI.dto.MealResponseDto;
import com.CalorieAPI.model.Meal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MealMapper {
    Meal toEntity(MealRequestDto dto);
    MealResponseDto toDto(Meal entity);
}
