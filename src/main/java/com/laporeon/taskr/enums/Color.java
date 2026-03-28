package com.laporeon.taskr.enums;

public enum Color {
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    RESET("\u001B[0m");

    private final String ansiCode;

    Color(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    @Override
    public String toString() {
        return ansiCode;
    }
}
