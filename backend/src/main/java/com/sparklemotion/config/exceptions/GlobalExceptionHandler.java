package com.sparklemotion.config.exceptions;


import com.sparklemotion.config.Error;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Error> handleException(ValidationException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Error.USER_EXISTS);
    }

    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public ResponseEntity<Error> handleUserException(ServiceException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Error.USER_DISABLED);
    }

    @ResponseBody
    @ExceptionHandler(value = ResourceNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Error.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(AuthException.class)
    public ResponseEntity<Error> handleAuthException(AuthException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Error.INVALID_CREDENTIALS);
    }
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Error> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Error.INVALID_CREDENTIALS);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Error> handleBadCredentialsException(BadCredentialsException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Error.INVALID_CREDENTIALS);
    }
}






