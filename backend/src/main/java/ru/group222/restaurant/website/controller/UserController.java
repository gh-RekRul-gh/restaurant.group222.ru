package ru.group222.restaurant.website.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import ru.group222.restaurant.website.dto.request.PasswordEditDto;
import ru.group222.restaurant.website.dto.request.PasswordResetDto;
import ru.group222.restaurant.website.dto.request.SupportContactDto;
import ru.group222.restaurant.website.dto.request.UserEditDto;
import ru.group222.restaurant.website.dto.request.UserEmailDto;
import ru.group222.restaurant.website.dto.request.UserLoginDto;
import ru.group222.restaurant.website.dto.request.UserRegisterDto;
import ru.group222.restaurant.website.dto.response.ApiResponseDto;
import ru.group222.restaurant.website.dto.response.UserInfoResponseDto;
import ru.group222.restaurant.website.dto.UserIdDto;
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
    ResponseEntity<ApiResponseDto<UserIdDto>> registerUser(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        UserIdDto userIdDto = userService.registerUser(userRegisterDto);
        return ResponseEntity.ok(ApiResponseDto.success(userIdDto));
    }

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Введен неверный пароль"),
            @ApiResponse(responseCode = "404", description = "Пользователь с данным email не зарегистрирован")
    })
    ResponseEntity<ApiResponseDto<UserIdDto>> loginUser(@Valid @RequestBody UserLoginDto userLoginDto) {
        UserIdDto userIdDto = userService.loginUser(userLoginDto);
        return ResponseEntity.ok(ApiResponseDto.success(userIdDto));
    }

    @PostMapping("/password-recovery")
    @Operation(summary = "Получение кода для обновления пароля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    ResponseEntity<ApiResponseDto<Void>> forgotPassword(@Valid @RequestBody UserEmailDto userEmailDto) {
        userService.forgotPassword(userEmailDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @PatchMapping("/password-reset")
    @Operation(summary = "Смена пароля с кодом")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "Код неверен или устарел")
    })
    ResponseEntity<ApiResponseDto<Void>> resetPassword(@Valid @RequestBody PasswordResetDto passwordResetDto) {
        userService.resetPassword(passwordResetDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @GetMapping("/{userId}")
    @Operation(summary = "Получение информации о пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    ResponseEntity<ApiResponseDto<UserInfoResponseDto>> getUser(
            @PathVariable @Parameter(description = "Id пользователя", example = "1") Long userId
    ) {
        UserInfoResponseDto userInfoResponseDto = userService.getUser(userId);
        return ResponseEntity.ok(ApiResponseDto.success(userInfoResponseDto));
    }

    @PatchMapping("/edit")
    @Operation(summary = "Редактирование профиля пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    ResponseEntity<ApiResponseDto<Void>> userEditProfile(@Valid @RequestBody UserEditDto userEditDto) {
        userService.editUser(userEditDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @PatchMapping("/edit/password")
    @Operation(summary = "Редактирование пароля пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    })
    ResponseEntity<ApiResponseDto<Void>> userEditPassword(@Valid @RequestBody PasswordEditDto passwordEditDto) {
        userService.editUserPassword(passwordEditDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }

    @PostMapping("/support")
    @Operation(summary = "Связь с тех. поддержкой")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    ResponseEntity<ApiResponseDto<Void>> supportContact(@Valid @RequestBody SupportContactDto supportContactDto) {
        userService.supportContact(supportContactDto);
        return ResponseEntity.ok(ApiResponseDto.success());
    }
}
