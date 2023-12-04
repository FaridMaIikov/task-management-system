package com.company.taskmanagementsystem.model.enums;

public enum EStatus {
    TODO("TODO"),
    IN_PROGRESS("IN_PROGRESS"),
    CANCELED("CANCELED"),
    DONE("DONE");

    private final String value;

    EStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
