package com.laporeon.taskr.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TaskPriority {

    LOW("low", "●○○", "\u001B[32m"),
    MEDIUM("medium", "●●○", "\u001B[33m"),
    HIGH("high", "●●●", "\u001B[31m");

    private final String value;
    private final String symbol;
    private final String color;

    TaskPriority(String value, String symbol, String color) {
        this.value = value;
        this.symbol = symbol;
        this.color = color;
    }

    private static final String INVALID_PRIORITY_VALUE_ERROR = "Invalid priority '%s'. Valid options are: low, medium or high";

    public static TaskPriority fromString(String priority) {
        return Arrays.stream(TaskPriority.values())
                .filter(task -> task.value.equalsIgnoreCase(priority))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PRIORITY_VALUE_ERROR.formatted(priority)));
    }
}
