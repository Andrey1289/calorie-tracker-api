package com.CalorieAPI.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Builder
public class FoodIntakeResponseDto {
    private Long id;
    private Long userId;
    private Set<MealResponseDto> meals;
    private LocalDate date;
    private LocalTime time;
}