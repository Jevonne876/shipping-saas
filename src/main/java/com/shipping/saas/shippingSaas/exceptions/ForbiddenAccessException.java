package com.shipping.saas.shippingSaas.exceptions;

public class ForbiddenAccessException extends RuntimeException{
    public ForbiddenAccessException(String message) {
        super(message);
    }
}
