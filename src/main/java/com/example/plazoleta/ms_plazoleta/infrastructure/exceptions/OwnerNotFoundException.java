package com.example.plazoleta.ms_plazoleta.infrastructure.exceptions;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(String message) {
        super(message);
    }
}
