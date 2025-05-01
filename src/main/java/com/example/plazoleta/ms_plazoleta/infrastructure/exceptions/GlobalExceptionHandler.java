package com.example.plazoleta.ms_plazoleta.infrastructure.exceptions;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<String> handleUnauthorized(UnauthorizedException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
    }

    @ExceptionHandler(IllegalLogoException.class)
    public ResponseEntity<Map<String, String>> handleIllegalLogo(IllegalLogoException ex) {
        return buildBadRequest(ex.getMessage());
    }
    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleOwnerNotFound(OwnerNotFoundException ex) {
        return buildBadRequest(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        return buildBadRequest(ex.getMessage());
    }


    @ExceptionHandler(InvalidNitException.class)
    public ResponseEntity<Map<String, String>> handleInvalidNit(InvalidNitException ex) {
        return buildBadRequest(ex.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneric(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno: " + ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        return buildBadRequest(ErrorFieldsMessages.DISH_CATEGORY_INVALID);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException ex) {
        return buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalCategoryException.class)
    public ResponseEntity<Map<String, String>> handleIllegalCategory(IllegalCategoryException ex) {
        return buildBadRequest(ex.getMessage());
    }


    // Métodos auxiliares para evitar duplicación
    private ResponseEntity<Map<String, String>> buildBadRequest(String message) {
        return buildResponse(message, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<Map<String, String>> buildResponse(String message, HttpStatus status) {
        Map<String, String> response = new HashMap<>();
        response.put("error", message);
        return ResponseEntity.status(status).body(response);
    }
}
