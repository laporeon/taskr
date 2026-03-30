package com.laporeon.taskr.enums;

public enum Color {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    RESET("\u001B[0m"),
    BOLD_UNDERLINE("\u001B[1;4m"),
    YELLOW("\u001B[33m"),
    ORANGE("\u001B[38;5;166m"),
    BLUE("\u001B[34m"),
    GRAY("\u001B[90m"),
    STRIKETHROUGH("\u001B[9m"),
    GRAY_STRIKETHROUGH("\u001B[90m\u001B[9m");

    private final String ansiCode;

    Color(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String apply(String symbol) {
        return ansiCode + symbol + Color.RESET.ansiCode;
    }

    @Override
    public String toString() {
        return ansiCode;
    }
}
