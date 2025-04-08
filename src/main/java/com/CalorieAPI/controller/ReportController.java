package com.CalorieAPI.controller;

import com.CalorieAPI.dto.report.DailyLimitCheckDto;
import com.CalorieAPI.dto.report.DailyReportDto;
import com.CalorieAPI.dto.report.PeriodReportDto;
import com.CalorieAPI.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/daily/{userId}/{date}")
    public DailyReportDto getDailyReport(
            @PathVariable Long userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reportService.generateDailyReport(userId, date);
    }

    @GetMapping("/period/{userId}")
    public PeriodReportDto getPeriodReport(
            @PathVariable Long userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        return reportService.generatePeriodReport(userId, start, end);
    }
    @GetMapping("/check-daily-limit/{userId}/{date}")
    public DailyLimitCheckDto checkDailyLimit(
            @PathVariable Long userId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        DailyReportDto report = generateDailyReport(userId, date);
        boolean isWithinLimit = report.getTotalCalories() <= report.getDailyNorm();

        return DailyLimitCheckDto.builder()
                .date(date)
                .totalCalories(report.getTotalCalories())
                .dailyNorm(report.getDailyNorm())
                .isWithinLimit(isWithinLimit)
                .build();
    }
}