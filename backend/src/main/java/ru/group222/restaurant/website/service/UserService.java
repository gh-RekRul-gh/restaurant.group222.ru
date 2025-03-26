package ru.group222.restaurant.website.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.group222.restaurant.website.dto.request.PasswordResetDto;
import ru.group222.restaurant.website.dto.request.UserEmailDto;
import ru.group222.restaurant.website.dto.request.UserLoginDto;
import ru.group222.restaurant.website.dto.request.UserRegisterDto;
import ru.group222.restaurant.website.dto.response.UserDto;
import ru.group222.restaurant.website.dto.response.UserResponseIdDto;
import ru.group222.restaurant.website.exception.AlreadyExistsException;
import ru.group222.restaurant.website.exception.EntityNotFoundException;
import ru.group222.restaurant.website.exception.WrongCredentialsException;
import ru.group222.restaurant.website.mapping.UserMapper;
import ru.group222.restaurant.website.model.Token;
import ru.group222.restaurant.website.model.User;
import ru.group222.restaurant.website.repository.UserRepository;

import java.util.Locale;


@Service
@RequiredArgsConstructor
@Log4j2
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TokenService tokenService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public User findUserByEmailOrElseThrow(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    String message = String.format("User not found with email: %s", email);
                    log.warn(message);
                    return new EntityNotFoundException(message);
                });
    }

    public User findUserByIdOrElseThrow(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> {
                    String message = String.format("User not found with id: %d", id);
                    log.warn(message);
                    return new EntityNotFoundException(message);
                });
    }

    public void verifyPassword(String password, User user) {
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            String message = String.format("Wrong password login for user with email: %s", user.getEmail());
            log.warn(message);
            throw new WrongCredentialsException(message);
        }
    }

    public UserResponseIdDto registerUser(UserRegisterDto userRegisterDto) {
        String newUserEmail = userRegisterDto.email().toLowerCase(Locale.ROOT);
        log.info("New user's to register email: {}", newUserEmail);
        if (userRepository.existsByEmail(newUserEmail)) {
            String message = String.format("Email is taken: %s", newUserEmail);
            log.warn(message);
            throw new AlreadyExistsException(message);
        }
        User newUser = userMapper.userRegisterDtoToUser(userRegisterDto, passwordEncoder);

        userRepository.save(newUser);
        log.info("New user was added with email: {}", newUser.getEmail());

        return new UserResponseIdDto(newUser.getId());
    }

    public UserResponseIdDto loginUser(UserLoginDto userLoginDto) {
        String userEmail = userLoginDto.email().toLowerCase(Locale.ROOT);
        User user = findUserByEmailOrElseThrow(userEmail);

        verifyPassword(userLoginDto.password(), user);

        log.info("User logged in with email: {}", user.getEmail());

        return new UserResponseIdDto(user.getId());
    }

    public void forgotPassword(UserEmailDto userEmailDto) {
        String userEmail = userEmailDto.email().toLowerCase(Locale.ROOT);
        User user = findUserByEmailOrElseThrow(userEmail);
        Token token = tokenService.createUserToken(user);

        emailService.sendEmail(user.getEmail(), String.format(
                TokenService.PASSWORD_RESET_MESSAGE, token.getCode()), "Password reset");

        log.info("Password reset code is sent");
    }

    public void resetPassword(PasswordResetDto passwordResetDto) {
        Token token = tokenService.findTokenByCodeOrElseThrow(passwordResetDto.code());
        tokenService.checkTokenExpiration(token);
        User user = token.getUser();
        user.setPasswordHash(passwordEncoder.encode(passwordResetDto.password()));
        userRepository.save(user);

        tokenService.deleteToken(token);

        log.info("Password is reset for user with email: {}", user.getEmail());
    }

    public UserDto getUser(Long id) {
        User user = findUserByIdOrElseThrow(id);

        log.info("User is found with id: {}", id);

        return userMapper.userToUserDto(user);
    }
}
