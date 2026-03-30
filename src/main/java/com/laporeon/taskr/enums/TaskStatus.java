package com.laporeon.taskr.enums;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum TaskStatus {

    TODO("todo", "[ ]", Color.ORANGE),
    IN_PROGRESS("in-progress", "[~]", Color.BLUE),
    DONE("done","[✓]", Color.GREEN);         // Green

    private final String value;
    private final String symbol;
    private final Color color;

    TaskStatus(String value, String symbol, Color color) {
        this.value = value;
        this.symbol = symbol;
        this.color = color;
    }

    public String getColoredSymbol() {
        return color.apply(symbol);
    }

    private static final String INVALID_STATUS_VALUE_ERROR = "Invalid status '%s'. Valid options are: todo, in-progress or done";

    public static TaskStatus fromString(String status) {
        return Arrays.stream(TaskStatus.values())
                .filter(s -> s.value.equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(INVALID_STATUS_VALUE_ERROR.formatted(status)));
    }
}
