package com.sparklemotion.config.exceptions;

import com.sparklemotion.config.Error;

public class ResourceNotFoundException extends RuntimeException {

    private final String message;

    public ResourceNotFoundException(Error message) {
        this.message = String.valueOf(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
