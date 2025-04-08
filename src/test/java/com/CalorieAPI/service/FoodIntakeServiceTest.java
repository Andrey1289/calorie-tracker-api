package com.CalorieAPI.service;

import com.CalorieAPI.repository.FoodIntakeRepository;
import com.CalorieAPI.repository.MealRepository;
import com.CalorieAPI.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class FoodIntakeServiceTest {
    @Mock
    private FoodIntakeRepository foodIntakeRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private FoodIntakeService foodIntakeService;

    @Test
    void createFoodIntake_ValidRequest_ReturnsDto() {
        // Тестовая логика
    }
}