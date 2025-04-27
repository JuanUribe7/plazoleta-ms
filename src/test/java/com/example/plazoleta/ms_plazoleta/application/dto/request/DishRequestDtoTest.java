package com.example.plazoleta.ms_plazoleta.application.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DishRequestDtoTest {

    @Test
    void testConstructorAndGetters() {
        DishRequestDto dto = new DishRequestDto("Dish", 1000, "Description", "http://image.com", "Category", 1L, 2L);

        assertEquals("Dish", dto.getName());
        assertEquals(1000, dto.getPrice());
        assertEquals("Description", dto.getDescription());
        assertEquals("http://image.com", dto.getImageUrl());
        assertEquals("Category", dto.getCategory());
        assertEquals(1L, dto.getRestaurantId());
        assertEquals(2L, dto.getOwnerId());
    }

    @Test
    void testSettersAndGetters() {
        DishRequestDto dto = new DishRequestDto(null, null, null, null, null, null, null);

        dto.setName("Dish Name");
        dto.setPrice(2000);
        dto.setDescription("New Description");
        dto.setImageUrl("http://newimage.com");
        dto.setCategory("New Category");
        dto.setRestaurantId(3L);
        dto.setOwnerId(4L);

        assertEquals("Dish Name", dto.getName());
        assertEquals(2000, dto.getPrice());
        assertEquals("New Description", dto.getDescription());
        assertEquals("http://newimage.com", dto.getImageUrl());
        assertEquals("New Category", dto.getCategory());
        assertEquals(3L, dto.getRestaurantId());
        assertEquals(4L, dto.getOwnerId());
    }
}