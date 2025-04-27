package com.example.plazoleta.ms_plazoleta.infrastructure.exceptions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GlobalExceptionHandler();
    }

    @Test
    void handleUnauthorizedTest() {
        UnauthorizedException ex = new UnauthorizedException("Unauthorized access");
        ResponseEntity<String> response = handler.handleUnauthorized(ex);

        assertEquals(401, response.getStatusCodeValue());
        assertEquals("Unauthorized access", response.getBody());
    }

    @Test
    void handleIllegalLogoTest() {
        IllegalLogoException ex = new IllegalLogoException("Invalid logo URL");
        ResponseEntity<Map<String, String>> response = handler.handleIllegalLogo(ex);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid logo URL", response.getBody().get("error"));
    }

    @Test
    void handleOwnerNotFoundTest() {
        OwnerNotFoundException ex = new OwnerNotFoundException("Owner not found");
        ResponseEntity<Map<String, String>> response = handler.handleOwnerNotFound(ex);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Owner not found", response.getBody().get("error"));
    }

    @Test
    void handleIllegalArgumentTest() {
        IllegalArgumentException ex = new IllegalArgumentException("Illegal argument");
        ResponseEntity<Map<String, String>> response = handler.handleIllegalArgument(ex);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Illegal argument", response.getBody().get("error"));
    }

    @Test
    void handleInvalidNitTest() {
        InvalidNitException ex = new InvalidNitException("Invalid NIT");
        ResponseEntity<Map<String, String>> response = handler.handleInvalidNit(ex);

        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Invalid NIT", response.getBody().get("error"));
    }

    @Test
    void handleGenericTest() {
        Exception ex = new Exception("Generic error");
        ResponseEntity<String> response = handler.handleGeneric(ex);

        assertEquals(500, response.getStatusCodeValue());
        assertTrue(response.getBody().contains("Generic error"));
    }

    @Test
    void handleNotFoundTest() {
        NotFoundException ex = new NotFoundException("Not found");
        ResponseEntity<Map<String, String>> response = handler.handleNotFound(ex);

        assertEquals(404, response.getStatusCodeValue());
        assertEquals("Not found", response.getBody().get("error"));
    }
}
