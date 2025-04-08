package com.CalorieAPI.dto;

import com.CalorieAPI.model.Goal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private int age;
    private double weight;
    private double height;
    private Goal goal;
    private double dailyCalorieNorm;
    private LocalDateTime createdAt;
}
