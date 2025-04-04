package com.CalorieAPI.service;

import com.CalorieAPI.dto.UserRequestDto;
import com.CalorieAPI.dto.UserResponseDto;
import com.CalorieAPI.exceptions.EmailAlreadyExistsException;
import com.CalorieAPI.model.Goal;
import com.CalorieAPI.model.User;
import com.CalorieAPI.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.within;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldCalculateCorrectCaloriesForWeightLoss() {
        // Given
        UserRequestDto request = UserRequestDto.builder()
                .name("Test User")
                .email("test@example.com")
                .age(30)
                .weight(70.0)
                .height(175.0)
                .goal(Goal.LOSE_WEIGHT)
                .build();

        when(userRepository.save(any(User.class))).thenAnswer(inv -> {
            User user = inv.getArgument(0);
            user.setId(1L);
            return user;
        });

        // When
        UserResponseDto response = userService.createUser(request);

        // Then
        double expectedBMR = 88.362 + (13.397 * 70) + (4.799 * 175) - (5.677 * 30);
        assertThat(response.getDailyCalorieNorm())
                .isEqualTo(expectedBMR * 0.85, within(0.01));
    }
    @Test
    void shouldThrowExceptionWhenEmailExists() {
        // Создаем DTO через builder
        UserRequestDto request = UserRequestDto.builder()
                .email("existing@test.com")
                .name("Test User")
                .age(30)
                .weight(70.0)
                .height(175.0)
                .goal(Goal.MAINTAIN)
                .build();

        // Мокируем проверку email
        when(userRepository.existsByEmail("existing@test.com")).thenReturn(true);

        // Проверяем исключение
        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.createUser(request);
        });

        // Дополнительно можно проверить сообщение исключения
        EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class,
                () -> userService.createUser(request));
        assertThat(exception.getMessage())
                .isEqualTo("Email 'existing@test.com' is already registered");
    }
}
