package com.tienda.appclient.exception;

/**
 * Excepción para reglas de negocio violadas (400 Bad Request).
 */
public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException() {
        super();
    }

    public BusinessRuleException(String message) {
        super(message);
    }

    public BusinessRuleException(String message, Throwable cause) {
        super(message, cause);
    }
}

