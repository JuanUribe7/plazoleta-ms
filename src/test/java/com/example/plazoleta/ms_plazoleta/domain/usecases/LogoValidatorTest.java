package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.utils.validation.LogoValidator;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.IllegalLogoException;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.*;

public class LogoValidatorTest {

    @Test
    void urlNullShouldFail() {
        Exception exception = assertThrows(IllegalLogoException.class, () -> LogoValidator.validate(null));
        assertEquals("La URL del logo no puede estar vacía", exception.getMessage());
    }

    @Test
    void invalidUrlFormatShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> LogoValidator.validate("ftp://logo.com/image"));
        assertEquals("La URL del logo debe ser válida y comenzar con http o https", exception.getMessage());
    }

    @Test
    void localUrlShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> LogoValidator.validate("http://localhost/logo.png"));
        assertEquals("La URL del logo debe ser válida y comenzar con http o https", exception.getMessage());
    }

    @Test
    void invalidFileExtensionShouldFail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> LogoValidator.validate("http://example.com/logo.bmp"));
        assertEquals("La URL del logo debe terminar en .png, .jpg, .jpeg o .svg", exception.getMessage());
    }

    @Test
    void testPrivateConstructorOfLogoValidator() throws Exception {
        Constructor<LogoValidator> constructor = LogoValidator.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        Exception exception = assertThrows(InvocationTargetException.class, constructor::newInstance);
        assertTrue(exception.getCause() instanceof UnsupportedOperationException);
        assertEquals("Clase utilitaria, no debe instanciarse.", exception.getCause().getMessage());
    }
}