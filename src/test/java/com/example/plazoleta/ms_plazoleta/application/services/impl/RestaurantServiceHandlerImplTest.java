package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.RestaurantDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.mappers.RestaurantSimpleDtoMapper;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IRestaurantServicePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RestaurantServiceHandlerImplTest {

    private IRestaurantServicePort restaurantServicePort;
    private RestaurantDtoMapper mapper;
    private RestaurantSimpleDtoMapper simpleMapper;
    private RestaurantServiceHandlerImpl service;

    @BeforeEach
    void setUp() {
        restaurantServicePort = mock(IRestaurantServicePort.class);
        mapper = mock(RestaurantDtoMapper.class);
        service = new RestaurantServiceHandlerImpl(restaurantServicePort, mapper, simpleMapper);
    }

    @Test
    void createRestaurant_success() {
        RestaurantRequestDto dto = new RestaurantRequestDto("Test", "123", "Address", "123456", "url", 1L);
        Restaurant model = new Restaurant();
        Restaurant saved = new Restaurant();
        RestaurantResponseDto responseDto = new RestaurantResponseDto(1L, "Test", "Address", "url");

        when(mapper.toModel(dto)).thenReturn(model);
        when(restaurantServicePort.createRestaurant(model)).thenReturn(saved);
        when(mapper.toResponseDto(saved)).thenReturn(responseDto);

        RestaurantResponseDto result = service.createRestaurant(dto);

        assertEquals(responseDto, result);
        verify(mapper).toModel(dto);
        verify(restaurantServicePort).createRestaurant(model);
        verify(mapper).toResponseDto(saved);
    }

    @Test
    void validateOwner_true() {
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(1L);
        when(restaurantServicePort.findById(1L)).thenReturn(Optional.of(restaurant));

        boolean result = service.validateOwner(1L, 1L);

        assertTrue(result);
    }

    @Test
    void validateOwner_false() {
        Restaurant restaurant = new Restaurant();
        restaurant.setOwnerId(2L);
        when(restaurantServicePort.findById(1L)).thenReturn(Optional.of(restaurant));

        boolean result = service.validateOwner(1L, 1L);

        assertFalse(result);
    }

    @Test
    void validateOwner_restaurantNotFound() {
        when(restaurantServicePort.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.validateOwner(1L, 1L));

        assertEquals("Restaurante no existe", exception.getMessage());
    }
}