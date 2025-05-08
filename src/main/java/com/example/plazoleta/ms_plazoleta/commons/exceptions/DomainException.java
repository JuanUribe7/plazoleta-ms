
package com.example.plazoleta.ms_plazoleta.commons.exceptions;

public abstract class DomainException extends RuntimeException {
    public DomainException(String message) {
        super(message);
    }
}
