package com.jedco.jedcoengineeringspring.config;

public enum LineEnum {
    LINE_1("Line 1"),
    LINE_2("Line 2"),
    LINE_3("Line 3");

    private final String value;

    LineEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
