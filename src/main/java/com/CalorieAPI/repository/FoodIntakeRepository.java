package com.CalorieAPI.repository;

import com.CalorieAPI.model.FoodIntake;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FoodIntakeRepository extends JpaRepository<FoodIntake, Long> {
    List<FoodIntake> findByUserIdAndDate(Long userId, LocalDate date);
}