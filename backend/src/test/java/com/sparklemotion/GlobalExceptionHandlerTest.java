package com.sparklemotion;

import com.sparklemotion.config.Error;
import com.sparklemotion.config.exceptions.*;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {
    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    public void testHandleException() {
        ValidationException exception = new ValidationException("Validation failed");
        ResponseEntity<Error> response = exceptionHandler.handleException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Error.USER_EXISTS, response.getBody());
    }

    @Test
    public void testHandleUserException() {
        ServiceException exception = new ServiceException("Service exception");
        ResponseEntity<Error> response = exceptionHandler.handleUserException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(Error.USER_DISABLED, response.getBody());
    }

    @Test
    public void testHandleResourceNotFoundException() {
        ResourceNotFoundException exception = new ResourceNotFoundException(Error.ACTION_NOT_FOUND);
        ResponseEntity<Error> response = exceptionHandler.handleResourceNotFoundException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals(Error.INTERNAL_SERVER_ERROR, response.getBody());
    }

    @Test
    public void testHandleAuthException() {
        AuthException exception = new AuthException();
        ResponseEntity<Error> response = exceptionHandler.handleAuthException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(Error.INVALID_CREDENTIALS, response.getBody());
    }

    @Test
    public void testHandleInvalidCredentialsException() {
        InvalidCredentialsException exception = new InvalidCredentialsException("Invalid credentials");
        ResponseEntity<Error> response = exceptionHandler.handleInvalidCredentialsException(exception);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals(Error.INVALID_CREDENTIALS, response.getBody());
    }

    @Test
    public void testHandleBadCredentialsException() {
        BadCredentialsException exception = new BadCredentialsException("Bad credentials");
        ResponseEntity<Error> response = exceptionHandler.handleBadCredentialsException(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals(Error.INVALID_CREDENTIALS, response.getBody());
    }
}
