package com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant;

import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.IllegalLogoException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

class LogoValidatorTest {

    @Test
    void constructorShouldThrowException() throws Exception {
        Constructor<LogoValidator> constructor = LogoValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        InvocationTargetException exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }

    @Test
    void urlShouldNotBeNull() {
        Exception exception = assertThrows(IllegalLogoException.class, () -> LogoValidator.validate(null));
        assertEquals("La URL del logo no puede estar vacía", exception.getMessage());
    }

    @Test
    void urlShouldNotBeEmpty() {
        Exception exception = assertThrows(IllegalLogoException.class, () -> LogoValidator.validate("   "));
        assertEquals("La URL del logo no puede estar vacía", exception.getMessage());
    }

    @Test
    void urlShouldBeValidHttpOrHttps() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> LogoValidator.validate("ftp://invalid.url/image.png"));
        assertEquals("La URL del logo debe ser válida y comenzar con http o https", exception.getMessage());
    }

    @Test
    void urlShouldNotBeLocalhost() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> LogoValidator.validate("http://localhost/image.png"));
        assertEquals("La URL del logo no puede ser local", exception.getMessage());
    }

    @Test
    void urlShouldHaveValidExtension() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> LogoValidator.validate("http://example.com/image.bmp"));
        assertEquals("La URL del logo debe terminar en .png, .jpg, .jpeg o .svg", exception.getMessage());
    }

    @Test
    void validUrlShouldPass() {
        assertDoesNotThrow(() -> LogoValidator.validate("https://example.com/logo.png"));
        assertDoesNotThrow(() -> LogoValidator.validate("http://example.com/image.jpg"));
        assertDoesNotThrow(() -> LogoValidator.validate("http://example.com/image.jpeg"));
        assertDoesNotThrow(() -> LogoValidator.validate("https://example.com/icon.svg"));
    }
}
