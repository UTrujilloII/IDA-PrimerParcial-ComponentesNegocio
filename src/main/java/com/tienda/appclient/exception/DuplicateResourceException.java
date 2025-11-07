package com.tienda.appclient.exception;

/**
 * Excepción para recursos duplicados (409 Conflict).
 */
public class DuplicateResourceException extends RuntimeException {

    public DuplicateResourceException() {
        super();
    }

    public DuplicateResourceException(String message) {
        super(message);
    }

    public DuplicateResourceException(String message, Throwable cause) {
        super(message, cause);
    }
}

