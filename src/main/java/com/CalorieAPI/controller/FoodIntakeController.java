package com.CalorieAPI.controller;

import com.CalorieAPI.dto.FoodIntakeRequestDto;
import com.CalorieAPI.dto.FoodIntakeResponseDto;
import com.CalorieAPI.service.FoodIntakeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/food-intakes")
@RequiredArgsConstructor
public class FoodIntakeController {
    private final FoodIntakeService foodIntakeService;

    @PostMapping
    public ResponseEntity<FoodIntakeResponseDto> createFoodIntake(
            @Valid @RequestBody FoodIntakeRequestDto request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(foodIntakeService.createFoodIntake(request));
    }
}
