package com.example.plazoleta.ms_plazoleta.infrastructure.endpoints.rest;


import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.services.DishServiceHandler;
import com.example.plazoleta.ms_plazoleta.application.services.RestaurantServiceHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RestaurantControllerTest {

    private RestaurantServiceHandler restaurantServiceHandler;

    private RestaurantController restaurantController;

    @BeforeEach
    void setUp() {
        restaurantServiceHandler = mock(RestaurantServiceHandler.class);

        restaurantController = new RestaurantController(restaurantServiceHandler);
    }

    @Test
    void createRestaurant_ShouldReturnCreatedRestaurant() {
        // Arrange
        RestaurantRequestDto requestDto = new RestaurantRequestDto();
        RestaurantResponseDto responseDto = new RestaurantResponseDto();
        when(restaurantServiceHandler.createRestaurant(requestDto)).thenReturn(responseDto);

        // Act
        ResponseEntity<RestaurantResponseDto> response = restaurantController.createRestaurant(requestDto);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(responseDto, response.getBody());
        verify(restaurantServiceHandler).createRestaurant(requestDto);
    }
}