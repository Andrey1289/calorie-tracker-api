package com.CalorieAPI.service;

import com.CalorieAPI.dto.UserRequestDto;
import com.CalorieAPI.dto.UserResponseDto;
import com.CalorieAPI.exceptions.EmailAlreadyExistsException;
import com.CalorieAPI.mapper.UserMapper;
import com.CalorieAPI.model.User;
import com.CalorieAPI.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponseDto createUser(UserRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        User user = userMapper.toEntity(request);
        user.setDailyCalorieNorm(calculateDailyCalories(user));

        return userMapper.toDto(userRepository.save(user));
    }

    private double calculateDailyCalories(User user) {
        // Формула Харриса-Бенедикта
        double bmr = 88.362 + (13.397 * user.getWeight())
                + (4.799 * user.getHeight())
                - (5.677 * user.getAge());

        return switch (user.getGoal()) {
            case LOSE_WEIGHT -> bmr * 0.85;
            case MAINTAIN -> bmr;
            case GAIN_WEIGHT -> bmr * 1.15;
        };
    }
}
