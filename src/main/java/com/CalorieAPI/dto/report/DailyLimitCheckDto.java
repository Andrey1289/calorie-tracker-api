package com.CalorieAPI.dto.report;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class DailyLimitCheckDto {
    private LocalDate date;
    private double totalCalories;
    private double dailyNorm;
    private boolean isWithinLimit;
}