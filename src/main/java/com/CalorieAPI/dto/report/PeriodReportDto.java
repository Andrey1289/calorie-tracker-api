package com.CalorieAPI.dto.report;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class PeriodReportDto {
    private LocalDate startDate;
    private LocalDate endDate;
    private double averageCalories;
    private double averageProteins;
    private double averageFats;
    private double averageCarbs;
    private List<DailyReportDto> days;
}