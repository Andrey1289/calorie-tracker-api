package com.CalorieAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name= "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is mantadory")
    private String name;

    @Email(message = "Email should be valid")
    @Column(unique = true)
    private String email;

    @Min(value = 1 , message = "Age must be positive")
    private Integer age;

    @Positive(message = "Weight must be positive")
    private double weight; // kg

    @Positive(message = "Height must be positive")
    private double height; // cm

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Goal goal;

    private double dailyCalorieNorm;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
