package com.sparklemotion.config.exceptions;

public class InvalidCredentialsException extends CustomException {
    public InvalidCredentialsException(String message) {
        super("Invalid username or password.");
    }}

