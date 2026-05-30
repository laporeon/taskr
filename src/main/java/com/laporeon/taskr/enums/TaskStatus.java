package com.laporeon.taskr.enums;

import com.laporeon.taskr.exceptions.InvalidArgumentException;

import java.util.Arrays;

public enum TaskStatus {

    TODO("todo", "\u25A1", Color.YELLOW),
    IN_PROGRESS("in-progress", "\u25D0", Color.BLUE),
    DONE("done","\u2713", Color.GREEN);

    private final String value;
    private final String symbol;
    private final Color color;

    TaskStatus(String value, String symbol, Color color) {
        this.value = value;
        this.symbol = symbol;
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public String getColoredSymbol() {
        return color.apply(symbol);
    }

    private static final String INVALID_STATUS_VALUE_ERROR = "Invalid status '%s'. Valid options are: todo, in-progress or done";

    public static TaskStatus fromString(String status) {
        if (status == null) return null;

        return Arrays.stream(TaskStatus.values())
                .filter(s -> s.value.equalsIgnoreCase(status))
                .findFirst()
                .orElseThrow(() -> new InvalidArgumentException(INVALID_STATUS_VALUE_ERROR.formatted(status)));
    }

}
