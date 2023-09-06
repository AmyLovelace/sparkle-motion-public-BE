package com.sparklemotion.config.exceptions;

public class CustomException extends RuntimeException {
    private final String message;

    public CustomException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
