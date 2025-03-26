package ru.group222.restaurant.website.service.util;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ThreadLocalRandom;

@UtilityClass
public class CodeGenerator {
    public static String generateCode(int length) {
        if (length < 1) {
            throw new IllegalArgumentException("Code length must be at least 1");
        }

        int minValue = (int) Math.pow(10, (double) length - 1);

        int maxValueExcluded = (int) Math.pow(10, length);

        int code = ThreadLocalRandom.current().nextInt(minValue, maxValueExcluded);
        return String.valueOf(code);
    }
}
