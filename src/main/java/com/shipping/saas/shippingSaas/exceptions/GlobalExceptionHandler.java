package com.shipping.saas.shippingSaas.exceptions;

import com.shipping.saas.shippingSaas.domain.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PlatformUserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlatformUserNotFound(PlatformUserNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse("PLATFORM_USER_NOT_FOUND", exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForbiddenAccessException.class)
    public ResponseEntity<ErrorResponse> handleForbiddenAccess(ForbiddenAccessException exception) {
        ErrorResponse errorResponse = new ErrorResponse("FORBIDDEN_ACCESS", exception.getMessage(), HttpStatus.FORBIDDEN.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException exception) {
        ErrorResponse errorResponse = new ErrorResponse("INVALID_TOKEN", exception.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse("USERNAME_NOT_FOUND", exception.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponse> handleBadRequestException(BadRequestException exception) {
        ErrorResponse errorResponse = new ErrorResponse("BAD_REQUEST", exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateResourceException(DuplicateResourceException exception) {
        ErrorResponse errorResponse = new ErrorResponse("DUPLICATE_RESOURCE", exception.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
