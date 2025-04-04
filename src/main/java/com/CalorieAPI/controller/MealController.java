package com.CalorieAPI.controller;

import com.CalorieAPI.dto.MealRequestDto;
import com.CalorieAPI.dto.MealResponseDto;
import com.CalorieAPI.service.MealService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/meals")
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;

    @PostMapping
    public ResponseEntity<MealResponseDto> createMeal(
            @Valid @RequestBody MealRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mealService.createMeal(request));
    }

    @GetMapping
    public List<MealResponseDto> getAllMeals() {
        return mealService.getAllMeals();
    }
}
