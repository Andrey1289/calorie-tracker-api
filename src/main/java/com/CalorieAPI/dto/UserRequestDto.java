package com.CalorieAPI.dto;

import com.CalorieAPI.model.Goal;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequestDto {
    @NotBlank
    private String name;

    @Email
    private String email;

    @Min(1)
    private int age;

    @Positive
    private double weight;

    @Positive
    private double height;

    @NotNull(message = "Goal must be specified")
    private Goal goal;
}
