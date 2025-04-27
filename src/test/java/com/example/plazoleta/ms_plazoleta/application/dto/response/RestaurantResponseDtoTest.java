package com.example.plazoleta.ms_plazoleta.application.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantResponseDtoTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        RestaurantResponseDto dto = new RestaurantResponseDto(1L, "Restaurant Name", "123 Main St", "http://logo.url");

        assertEquals(1L, dto.getId());
        assertEquals("Restaurant Name", dto.getName());
        assertEquals("123 Main St", dto.getAddress());
        assertEquals("http://logo.url", dto.getUrlLogo());
    }

    @Test
    void testSettersAndGetters() {
        RestaurantResponseDto dto = new RestaurantResponseDto();
        dto.setId(2L);
        dto.setName("Another Name");
        dto.setAddress("456 Side St");
        dto.setUrlLogo("http://another.url");

        assertEquals(2L, dto.getId());
        assertEquals("Another Name", dto.getName());
        assertEquals("456 Side St", dto.getAddress());
        assertEquals("http://another.url", dto.getUrlLogo());
    }
}
