package com.CalorieAPI.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Data
@Builder
public class FoodIntakeRequestDto {
    @NotNull(message = "User ID is required")
    private Long userId;

    @NotEmpty(message = "At least one meal must be specified")
    private Set<Long> mealIds;

    @NotNull(message = "Date is required")
    private LocalDate date;

    @NotNull
    private LocalTime time;
}