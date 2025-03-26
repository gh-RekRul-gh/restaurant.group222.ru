package ru.group222.restaurant.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.group222.restaurant.website.dto.request.PasswordResetDto;
import ru.group222.restaurant.website.dto.request.UserEmailDto;
import ru.group222.restaurant.website.dto.request.UserLoginDto;
import ru.group222.restaurant.website.dto.request.UserRegisterDto;
import ru.group222.restaurant.website.dto.response.ApiResponseDto;
import ru.group222.restaurant.website.dto.response.UserDto;
import ru.group222.restaurant.website.dto.response.UserResponseIdDto;
import ru.group222.restaurant.website.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    @Operation(summary = "Регистрация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "409", description = "Конфликт - почта уже зарегистрирована")
    })
    ResponseEntity<ApiResponseDto<UserResponseIdDto>> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        UserResponseIdDto userResponseIdDto = userService.registerUser(userRegisterDto);
        return ResponseEntity.ok(ApiResponseDto.success(userResponseIdDto));
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Введен неверный пароль"),
            @ApiResponse(responseCode = "404", description = "Пользователь с данным email не зарегистрирован")
    })
    ResponseEntity<ApiResponseDto<UserResponseIdDto>> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        UserResponseIdDto userResponseIdDto = userService.loginUser(userLoginDto);
        return ResponseEntity.ok(ApiResponseDto.success(userResponseIdDto));
    }

    @PostMapping("/password-recovery")
    @Operation(summary = "Запрос на получение кода для обновления пароля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    ResponseEntity<ApiResponseDto<Void>> forgotPassword(@Valid @RequestBody UserEmailDto userEmailDto) {
        userService.forgotPassword(userEmailDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @PatchMapping("/password-reset")
    @Operation(summary = "Запрос на смену пароля с кодом")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Код неверен или устарел")
    })
    ResponseEntity<ApiResponseDto<Void>> resetPassword(@Valid @RequestBody PasswordResetDto passwordResetDto) {
        userService.resetPassword(passwordResetDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Запрос на получение информации о пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    ResponseEntity<ApiResponseDto<UserDto>> getUser(@Valid @PathVariable Long id) {
        UserDto userDto = userService.getUser(id);
        return ResponseEntity.ok(ApiResponseDto.success(userDto));
    }

}
