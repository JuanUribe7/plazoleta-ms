package com.example.plazoleta.ms_plazoleta.infrastructure.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDtoTest {

    @Test
    void testNoArgsConstructor() {
        UserResponseDto dto = new UserResponseDto();
        assertNotNull(dto);
    }

    @Test
    void testAllArgsConstructorAndGetters() {
        UserResponseDto dto = new UserResponseDto(1L, "Juan", "juan@example.com", "ADMIN", 10L);
        assertEquals(1L, dto.getId());
        assertEquals("Juan", dto.getName());
        assertEquals("juan@example.com", dto.getEmail());
        assertEquals("ADMIN", dto.getRole());
        assertEquals(10L, dto.getRestaurantId());
    }

    @Test
    void testSetters() {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(2L);
        dto.setName("Maria");
        dto.setEmail("maria@example.com");
        dto.setRole("USER");
        dto.setRestaurantId(5L);

        assertEquals(2L, dto.getId());
        assertEquals("Maria", dto.getName());
        assertEquals("maria@example.com", dto.getEmail());
        assertEquals("USER", dto.getRole());
        assertEquals(5L, dto.getRestaurantId());
    }
}
