package ru.group222.restaurant.website.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class ApiResponseDto<D> {

    private D data;

    private List<ApiError> errors = new ArrayList<>();

    public ApiResponseDto(D data) {
        this.data = data;
    }

    public static <D> ApiResponseDto<D> success(D data) {
        return new ApiResponseDto<>(data);
    }

    public static <D> ApiResponseDto<D> success() {
        return new ApiResponseDto<>(null);
    }

    public ApiResponseDto(D data, ApiError... apiErrors) {
        this(data);
        errors.addAll(Arrays.asList(apiErrors));
    }

    public static ApiResponseDto<Void> error(ApiError... errors) {
        return new ApiResponseDto<>(null, errors);
    }
}
