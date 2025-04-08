package com.CalorieAPI.exceptions;

public class MealNotFoundException extends RuntimeException {
    public MealNotFoundException(Long mealId) {
        super("Meal not found with id: " + mealId);
    }

    public MealNotFoundException(String message) {
        super(message);
    }
}