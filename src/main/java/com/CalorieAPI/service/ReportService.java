package com.CalorieAPI.service;

import com.CalorieAPI.dto.report.DailyReportDto;
import com.CalorieAPI.exceptions.UserNotFoundException;
import com.CalorieAPI.model.FoodIntake;
import com.CalorieAPI.model.User;
import com.CalorieAPI.repository.FoodIntakeRepository;
import com.CalorieAPI.repository.MealRepository;
import com.CalorieAPI.repository.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService {
    private final FoodIntakeRepository foodIntakeRepository;
    private final UserRepository userRepository;


    public DailyReportDto generateDailyReport(Long userId, LocalDate date) {
        log.info("Generating daily report for user {} on date {}", userId, date);

        // 1. Получаем пользователя
        User user = userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", userId);
                    return new UserNotFoundException(userId);
                });

        // 2. Получаем все приемы пищи за день
        List<FoodIntake> dailyIntakes = foodIntakeRepository.findByUserIdAndDate(userId, date);

        if (dailyIntakes.isEmpty()) {
            log.info("No food intakes found for user {} on date {}", userId, date);
            return buildEmptyReport(user, date);
        }

        // 3. Рассчитываем суммарные показатели
        NutritionTotals totals = calculateNutritionTotals(dailyIntakes);

        // 4. Формируем отчет
        return DailyReportDto.builder()
                .date(date)
                .totalCalories(totals.getCalories())
                .totalProteins(totals.getProteins())
                .totalFats(totals.getFats())
                .totalCarbs(totals.getCarbs())
                .dailyNorm(user.getDailyCalorieNorm())
                .isWithinLimit(totals.getCalories() <= user.getDailyCalorieNorm())
                .meals(mapToMealEntries(dailyIntakes))
                .build();
    }

    private NutritionTotals calculateNutritionTotals(List<FoodIntake> intakes) {
        NutritionTotals totals = new NutritionTotals();

        intakes.forEach(intake ->
                intake.getMeals().forEach(meal -> {
                    totals.addCalories(meal.getCalories());
                    totals.addProteins(meal.getProteins());
                    totals.addFats(meal.getFats());
                    totals.addCarbs(meal.getCarbohydrates());
                })
        );

        return totals;
    }

    private List<DailyReportDto.MealEntryDto> mapToMealEntries(List<FoodIntake> intakes) {
        return intakes.stream()
                .flatMap(intake -> intake.getMeals().stream()
                        .map(meal -> DailyReportDto.MealEntryDto.builder()
                                .name(meal.getName())
                                .calories(meal.getCalories())
                                .proteins(meal.getProteins())
                                .fats(meal.getFats())
                                .carbs(meal.getCarbohydrates())
                                .time(intake.getTime())
                                .build()))
                .collect(Collectors.toList());
    }

    private DailyReportDto buildEmptyReport(User user, LocalDate date) {
        return DailyReportDto.builder()
                .date(date)
                .totalCalories(0)
                .totalProteins(0)
                .totalFats(0)
                .totalCarbs(0)
                .dailyNorm(user.getDailyCalorieNorm())
                .isWithinLimit(true)
                .meals(Collections.emptyList())
                .build();
    }

    @Getter
    private static class NutritionTotals {
        private double calories;
        private double proteins;
        private double fats;
        private double carbs;

        public void addCalories(double value) { this.calories += value; }
        public void addProteins(double value) { this.proteins += value; }
        public void addFats(double value) { this.fats += value; }
        public void addCarbs(double value) { this.carbs += value; }
    }
}