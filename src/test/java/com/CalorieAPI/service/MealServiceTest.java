package com.CalorieAPI.service;

import com.CalorieAPI.dto.MealRequestDto;
import com.CalorieAPI.dto.MealResponseDto;
import com.CalorieAPI.model.Meal;
import com.CalorieAPI.repository.MealRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MealServiceTest {
    @Mock
    private MealRepository mealRepository;

    @InjectMocks
    private MealService mealService;

    @Test
    void createMeal_WithUniqueName_ReturnsMealDto() {
        MealRequestDto request = MealRequestDto.builder()
                .name("Овсянка")
                .calories(150)
                .build();

        when(mealRepository.existsByName("Овсянка")).thenReturn(false);
        when(mealRepository.save(any(Meal.class))).thenAnswer(inv -> {
            Meal meal = inv.getArgument(0);
            meal.setId(1L);
            return meal;
        });

        MealResponseDto response = mealService.createMeal(request);

        assertThat(response.getId()).isNotNull();
        assertThat(response.getName()).isEqualTo("Овсянка");
    }

}
