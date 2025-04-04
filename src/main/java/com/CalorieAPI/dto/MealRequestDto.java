package com.CalorieAPI.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MealRequestDto {
    @NotBlank
    private String name;

    @DecimalMin(value = "0.0", message = "Калории должны быть ≥ 0")
    private double calories;

    @DecimalMin("0.0")
    private double proteins;

    @DecimalMin("0.0")
    private double fats;

    @DecimalMin("0.0")
    private double carbohydrates;
}
