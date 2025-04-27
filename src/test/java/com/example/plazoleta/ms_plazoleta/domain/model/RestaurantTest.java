package com.example.plazoleta.ms_plazoleta.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {

    @Test
    void testNoArgsConstructorAndSetters() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setName("Test Restaurant");
        restaurant.setNit("123456789");
        restaurant.setAddress("Test Address");
        restaurant.setPhone("+1234567890");
        restaurant.setUrlLogo("http://logo.url");
        restaurant.setOwnerId(10L);

        assertEquals(1L, restaurant.getId());
        assertEquals("Test Restaurant", restaurant.getName());
        assertEquals("123456789", restaurant.getNit());
        assertEquals("Test Address", restaurant.getAddress());
        assertEquals("+1234567890", restaurant.getPhone());
        assertEquals("http://logo.url", restaurant.getUrlLogo());
        assertEquals(10L, restaurant.getOwnerId());
    }

    @Test
    void testAllArgsConstructor() {
        Restaurant restaurant = new Restaurant(2L, "Another Restaurant", "987654321", "Another Address", "+0987654321", "http://another.logo", 20L);

        assertEquals(2L, restaurant.getId());
        assertEquals("Another Restaurant", restaurant.getName());
        assertEquals("987654321", restaurant.getNit());
        assertEquals("Another Address", restaurant.getAddress());
        assertEquals("+0987654321", restaurant.getPhone());
        assertEquals("http://another.logo", restaurant.getUrlLogo());
        assertEquals(20L, restaurant.getOwnerId());
    }
}