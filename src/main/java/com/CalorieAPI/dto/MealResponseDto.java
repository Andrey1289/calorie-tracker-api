package com.CalorieAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MealResponseDto {
    private Long id;
    private String name;
    private double calories;
    private double proteins;
    private double fats;
    private double carbohydrates;
}