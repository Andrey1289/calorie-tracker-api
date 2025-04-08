package com.CalorieAPI.mapper;

import com.CalorieAPI.dto.FoodIntakeResponseDto;
import com.CalorieAPI.model.FoodIntake;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {MealMapper.class})
public interface FoodIntakeMapper {
    FoodIntakeResponseDto toDto(FoodIntake foodIntake);
}