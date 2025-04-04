package com.CalorieAPI.repository;

import com.CalorieAPI.model.Meal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MealRepository extends JpaRepository<Meal,Long> {
    Optional<Meal> findByName(String name);
    boolean existsByName(String name);
}
