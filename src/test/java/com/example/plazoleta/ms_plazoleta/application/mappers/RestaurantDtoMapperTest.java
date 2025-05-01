package com.example.plazoleta.ms_plazoleta.application.mappers;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.restaurant.RestaurantDtoMapper;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantDtoMapperTest {

    private final RestaurantDtoMapper mapper = Mappers.getMapper(RestaurantDtoMapper.class);

    @Test
    void testToModel() {
        RestaurantRequestDto dto = new RestaurantRequestDto("Restaurante Test", "123456789", "Calle 1", "+573001112233", "http://logo.com", 5L);
        Restaurant restaurant = mapper.toModel(dto);

        assertNull(restaurant.getId());
        assertEquals("Restaurante Test", restaurant.getName());
        assertEquals("123456789", restaurant.getNit());
        assertEquals("Calle 1", restaurant.getAddress());
        assertEquals("+573001112233", restaurant.getPhone());
        assertEquals("http://logo.com", restaurant.getUrlLogo());
        assertEquals(5L, restaurant.getOwnerId());
    }

    @Test
    void testToResponseDto() {
        Restaurant restaurant = new Restaurant(1L, "Restaurante Test", "123456789", "Calle 1", "+573001112233", "http://logo.com", 5L);
        RestaurantResponseDto dto = mapper.toResponseDto(restaurant);

        assertEquals(1L, dto.getId());
        assertEquals("Restaurante Test", dto.getName());
        assertEquals("Calle 1", dto.getAddress());
        assertEquals("http://logo.com", dto.getUrlLogo());
    }
}