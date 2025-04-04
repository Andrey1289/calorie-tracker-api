package com.CalorieAPI.service;

import com.CalorieAPI.dto.MealRequestDto;
import com.CalorieAPI.dto.MealResponseDto;
import com.CalorieAPI.exceptions.MealAlreadyExistsException;
import com.CalorieAPI.mapper.MealMapper;
import com.CalorieAPI.model.Meal;
import com.CalorieAPI.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MealService {
    private final MealRepository mealRepository;
    private final MealMapper mealMapper;

    public MealResponseDto createMeal(MealRequestDto request) {
        if (mealRepository.existsByName(request.getName())) {
            throw new MealAlreadyExistsException(request.getName());
        }

        Meal meal = mealMapper.toEntity(request);
        return mealMapper.toDto(mealRepository.save(meal));
    }

    public List<MealResponseDto> getAllMeals() {
        return mealRepository.findAll().stream()
                .map(mealMapper::toDto)
                .toList();
    }
}
