package com.CalorieAPI.service;

import com.CalorieAPI.dto.FoodIntakeRequestDto;
import com.CalorieAPI.dto.FoodIntakeResponseDto;
import com.CalorieAPI.exceptions.MealNotFoundException;
import com.CalorieAPI.exceptions.UserNotFoundException;
import com.CalorieAPI.mapper.FoodIntakeMapper;
import com.CalorieAPI.model.FoodIntake;
import com.CalorieAPI.model.Meal;
import com.CalorieAPI.model.User;
import com.CalorieAPI.repository.FoodIntakeRepository;
import com.CalorieAPI.repository.MealRepository;
import com.CalorieAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FoodIntakeService {
    private final FoodIntakeRepository foodIntakeRepository;
    private final UserRepository userRepository;
    private final MealRepository mealRepository;
    private final FoodIntakeMapper foodIntakeMapper;

    public FoodIntakeResponseDto createFoodIntake(FoodIntakeRequestDto request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new UserNotFoundException(request.getUserId()));

        Set<Meal> meals = request.getMealIds().stream()
                .map(mealId -> mealRepository.findById(mealId)
                        .orElseThrow(() -> new MealNotFoundException(mealId)))
                .collect(Collectors.toSet());
        if (meals.isEmpty()) {
            throw new MealNotFoundException("No valid meal IDs provided");
        }

        FoodIntake foodIntake = FoodIntake.builder()
                .user(user)
                .meals(meals)
                .date(request.getDate())
                .time(request.getTime())
                .build();

        return foodIntakeMapper.toDto(foodIntakeRepository.save(foodIntake));
    }
}