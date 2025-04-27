package com.example.plazoleta.ms_plazoleta.application.dto.request;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RestaurantRequestDtoTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        RestaurantRequestDto dto = new RestaurantRequestDto("Restaurante A", "123456789", "Calle 123", "+573001112233", "http://logo.com", 1L);

        assertEquals("Restaurante A", dto.getName());
        assertEquals("123456789", dto.getNit());
        assertEquals("Calle 123", dto.getAddress());
        assertEquals("+573001112233", dto.getPhone());
        assertEquals("http://logo.com", dto.getUrlLogo());
        assertEquals(1L, dto.getOwnerId());
    }

    @Test
    void testNoArgsConstructorAndSetters() {
        RestaurantRequestDto dto = new RestaurantRequestDto();

        dto.setName("Restaurante B");
        dto.setNit("987654321");
        dto.setAddress("Avenida 456");
        dto.setPhone("+573009876543");
        dto.setUrlLogo("http://logo2.com");
        dto.setOwnerId(2L);

        assertEquals("Restaurante B", dto.getName());
        assertEquals("987654321", dto.getNit());
        assertEquals("Avenida 456", dto.getAddress());
        assertEquals("+573009876543", dto.getPhone());
        assertEquals("http://logo2.com", dto.getUrlLogo());
        assertEquals(2L, dto.getOwnerId());
    }
}