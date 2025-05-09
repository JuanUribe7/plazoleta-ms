package com.example.plazoleta.ms_plazoleta.application.dto.response;

import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.CreateRestaurantResponseDto;
import org.junit.jupiter.api.Test;

class CreateRestaurantResponseDtoTest {

    @Test
    void testAllArgsConstructorAndGetters() {
        CreateRestaurantResponseDto dto = new CreateRestaurantResponseDto(1L, "Restaurant Name", "123 Main St", "http://logo.url");

        assertEquals(1L, dto.getId());
        assertEquals("Restaurant Name", dto.getName());
        assertEquals("123 Main St", dto.getAddress());
        assertEquals("http://logo.url", dto.getUrlLogo());
    }

    @Test
    void testSettersAndGetters() {
        CreateRestaurantResponseDto dto = new CreateRestaurantResponseDto();
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
