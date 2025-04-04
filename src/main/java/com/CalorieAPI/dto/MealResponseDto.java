package com.CalorieAPI.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MealResponseDto {
    private Long id;
    private String name;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
}