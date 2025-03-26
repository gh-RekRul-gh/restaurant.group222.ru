package ru.group222.restaurant.website.controller.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.group222.restaurant.website.dto.response.ApiError;
import ru.group222.restaurant.website.dto.response.ApiResponseDto;
import ru.group222.restaurant.website.exception.AlreadyExistsException;
import ru.group222.restaurant.website.exception.EntityNotFoundException;
import ru.group222.restaurant.website.exception.InvalidCodeException;
import ru.group222.restaurant.website.exception.WrongCredentialsException;

import java.util.List;

@ControllerAdvice
@Log4j2
public class ApiExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleAlreadyExistsException(AlreadyExistsException exception) {
        ApiError error = new ApiError(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(ApiResponseDto.error(error));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleEntityNotFoundException(EntityNotFoundException exception) {
        ApiError error = new ApiError(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ApiResponseDto.error(error));
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleWrongCredentialsException(WrongCredentialsException exception) {
        ApiError error = new ApiError(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDto.error(error));
    }

    @ExceptionHandler(InvalidCodeException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleInvalidCodeException(InvalidCodeException exception) {
        ApiError error = new ApiError(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDto.error(error));
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class,
            HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<ApiResponseDto<Void>> handleClientError(Exception exception) {
        String errorMessage = String.format("Client error: %s", exception.getMessage());
        ApiError error = new ApiError(errorMessage);
        log.warn(errorMessage);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDto.error(error));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponseDto<Void>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String errorMessage = "Validation failed with message" + exception.getMessage();
        log.warn(errorMessage);
        List<ApiError> validationErrors = exception.getBindingResult().getFieldErrors()
                .stream().map(fieldError -> new ApiError(fieldError.getDefaultMessage()))
                .toList();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseDto.error(validationErrors.toArray(new ApiError[0])));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDto<Void>> handleException(Exception exception) {
        ApiError error = new ApiError(exception.getMessage());
        log.error("Internal Server Error", exception);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponseDto.error(error));
    }
}
