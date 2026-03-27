package com.laporeon.taskr.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TaskStatus {

    TODO("todo", "[ ]", "\u001B[38;5;208m"),
    IN_PROGRESS("in-progress", "[~]", "\u001B[34m"),
    DONE("done","[✓]", "\u001B[32m");         // Green

    private final String value;
    private final String symbol;
    private final String color;

    TaskStatus(String value, String symbol, String color) {
        this.value = value;
        this.symbol = symbol;
        this.color = color;
    }

    private static final String INVALID_STATUS_VALUE_ERROR = "Invalid status '%s'. Valid options are: todo, in-progress or done";

    public static TaskStatus fromString(String status) {
        return Arrays.stream(TaskStatus.values())
                .filter(task -> task.value.equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_STATUS_VALUE_ERROR.formatted(status)));
    }
}
