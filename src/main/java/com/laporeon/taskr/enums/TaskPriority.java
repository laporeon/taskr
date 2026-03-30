package com.laporeon.taskr.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TaskPriority {

    LOW("low", "●○○", Color.GREEN),
    MEDIUM("medium", "●●○", Color.YELLOW),
    HIGH("high", "●●●", Color.RED);

    private final String value;
    private final String symbol;
    private final Color color;

    TaskPriority(String value, String symbol, Color color) {
        this.value = value;
        this.symbol = symbol;
        this.color = color;
    }

    public String getColoredSymbol() {
        return color.apply(symbol);
    }

    private static final String INVALID_PRIORITY_VALUE_ERROR = "Invalid priority '%s'. Valid options are: low, medium or high";

    public static TaskPriority fromString(String priority) {
        return Arrays.stream(TaskPriority.values())
                .filter(p -> p.value.equalsIgnoreCase(priority))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_PRIORITY_VALUE_ERROR.formatted(priority)));
    }
}
