package com.CalorieAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "meals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название блюда обязательно")
    @Column(unique = true)  // Уникальное название
    private String name;

    @PositiveOrZero(message = "Калории не могут быть отрицательными")
    private double calories;

    @PositiveOrZero(message = "Белки не могут быть отрицательными")
    private double proteins;

    @PositiveOrZero(message = "Жиры не могут быть отрицательными")
    private double fats;

    @PositiveOrZero(message = "Углеводы не могут быть отрицательными")
    private double carbohydrates;
}
