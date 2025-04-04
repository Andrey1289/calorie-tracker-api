package com.CalorieAPI.exceptions;

public class MealAlreadyExistsException extends RuntimeException{
    public MealAlreadyExistsException(String name) {
        super("Блюдо '" + name + "' уже существует");
    }
}
