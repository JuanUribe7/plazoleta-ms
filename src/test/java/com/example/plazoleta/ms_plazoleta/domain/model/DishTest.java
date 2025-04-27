package com.example.plazoleta.ms_plazoleta.domain.model;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DishTest {

    @Test
    void testDefaultConstructorAndSettersGetters() {
        Dish dish = new Dish();

        dish.setId(1L);
        dish.setName("Test Dish");
        dish.setPrice(15000);
        dish.setDescription("Delicious test dish");
        dish.setImageUrl("http://example.com/image.jpg");
        dish.setCategory("Main");
        dish.setRestaurantId(2L);
        dish.setOwnerId(3L);
        dish.setActive(true);

        assertEquals(1L, dish.getId());
        assertEquals("Test Dish", dish.getName());
        assertEquals(15000, dish.getPrice());
        assertEquals("Delicious test dish", dish.getDescription());
        assertEquals("http://example.com/image.jpg", dish.getImageUrl());
        assertEquals("Main", dish.getCategory());
        assertEquals(2L, dish.getRestaurantId());
        assertEquals(3L, dish.getOwnerId());
        assertTrue(dish.getActive());
    }

    @Test
    void testAllArgsConstructor() {
        Dish dish = new Dish(1L, "Test Dish", 15000, "Delicious test dish", "http://example.com/image.jpg", "Main", 2L, 3L, true);

        assertEquals(1L, dish.getId());
        assertEquals("Test Dish", dish.getName());
        assertEquals(15000, dish.getPrice());
        assertEquals("Delicious test dish", dish.getDescription());
        assertEquals("http://example.com/image.jpg", dish.getImageUrl());
        assertEquals("Main", dish.getCategory());
        assertEquals(2L, dish.getRestaurantId());
        assertEquals(3L, dish.getOwnerId());
        assertTrue(dish.getActive());
    }
}