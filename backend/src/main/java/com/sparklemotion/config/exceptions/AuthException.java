package com.sparklemotion.config.exceptions;


public class AuthException extends CustomException {
    public AuthException() {
        super("Authentication error.");
    }
}
