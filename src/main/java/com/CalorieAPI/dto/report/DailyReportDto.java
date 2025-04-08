package com.CalorieAPI.dto.report;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@Builder
public class DailyReportDto {
    private LocalDate date;
    private double totalCalories;
    private double totalProteins;
    private double totalFats;
    private double totalCarbs;
    private double dailyNorm;
    private boolean isWithinLimit;
    private List<MealEntryDto> meals;

    @Data
    @Builder
    public static class MealEntryDto {
        private String name;
        private double calories;
        private double proteins;
        private double fats;
        private double carbs;
        private LocalTime time;
    }
}