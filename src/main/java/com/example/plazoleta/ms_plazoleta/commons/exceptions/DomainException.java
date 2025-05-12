
package com.example.plazoleta.ms_plazoleta.commons.exceptions;

public abstract class DomainException extends RuntimeException {
    protected DomainException(String message) {
        super(message);
    }
}
