package com.example.plazoleta.ms_plazoleta.application.dto.response;

import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DishResponseDtoTest {

    @Test
    void testNoArgsConstructor() {
        DishResponseDto dto = new DishResponseDto();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        DishResponseDto dto = new DishResponseDto(1L, "Pizza", 15000, "Delicious", "http://image.com", "Fast Food", 2L, true);
        assertEquals(1L, dto.getId());
        assertEquals("Pizza", dto.getName());
        assertEquals(15000, dto.getPrice());
        assertEquals("Delicious", dto.getDescription());
        assertEquals("http://image.com", dto.getImageUrl());
        assertEquals("Fast Food", dto.getCategory());
        assertEquals(2L, dto.getRestaurantId());
        assertTrue(dto.getActive());
    }

    @Test
    void testSetters() {
        DishResponseDto dto = new DishResponseDto();
        dto.setId(1L);
        dto.setName("Burger");
        dto.setPrice(12000);
        dto.setDescription("Tasty");
        dto.setImageUrl("http://image.com/burger");
        dto.setCategory("Main Course");
        dto.setRestaurantId(3L);
        dto.setActive(false);

        assertEquals(1L, dto.getId());
        assertEquals("Burger", dto.getName());
        assertEquals(12000, dto.getPrice());
        assertEquals("Tasty", dto.getDescription());
        assertEquals("http://image.com/burger", dto.getImageUrl());
        assertEquals("Main Course", dto.getCategory());
        assertEquals(3L, dto.getRestaurantId());
        assertFalse(dto.getActive());
    }
}