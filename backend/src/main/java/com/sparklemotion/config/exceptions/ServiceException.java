
package com.sparklemotion.config.exceptions;

public class ServiceException extends RuntimeException {


    private final String message;

    public ServiceException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
