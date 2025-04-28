package com.example.plazoleta.ms_plazoleta.infrastructure.client;

import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.OwnerNotFoundException;
import feign.Response;
import feign.Request;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class CustomErrorDecoderTest {

    private final CustomErrorDecoder errorDecoder = new CustomErrorDecoder();

    @Test
    void testDecode404ReturnsOwnerNotFoundException() {
        Response response = Response.builder()
                .status(404)
                .reason("Not Found")
                .request(Request.create(Request.HttpMethod.GET, "http://test", Collections.emptyMap(), null, null, null))
                .build();

        Exception exception = errorDecoder.decode("testMethod", response);
        assertTrue(exception instanceof OwnerNotFoundException);
        assertEquals("El propietario no fue encontrado.", exception.getMessage());
    }

    @Test
    void testDecode500ReturnsRuntimeException() {
        Response response = Response.builder()
                .status(500)
                .reason("Internal Server Error")
                .request(Request.create(Request.HttpMethod.GET, "http://test", Collections.emptyMap(), null, null, null))
                .build();

        Exception exception = errorDecoder.decode("testMethod", response);
        assertTrue(exception instanceof RuntimeException);
        assertEquals("No existe un usuario con ese id.", exception.getMessage());
    }

    @Test
    void testDecodeOtherStatusUsesDefaultDecoder() {
        Response response = Response.builder()
                .status(400)
                .reason("Bad Request")
                .request(Request.create(Request.HttpMethod.GET, "http://test", Collections.emptyMap(), null, null, null))
                .build();

        Exception exception = errorDecoder.decode("testMethod", response);
        assertNotNull(exception);
        assertTrue(exception instanceof RuntimeException);
    }
}
