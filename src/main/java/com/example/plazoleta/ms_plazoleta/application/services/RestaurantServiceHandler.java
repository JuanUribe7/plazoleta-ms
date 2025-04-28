package com.example.plazoleta.ms_plazoleta.application.services;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;

public interface RestaurantServiceHandler {

    RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurant);
    boolean validateOwner(Long restaurantId, Long ownerId);
    boolean isOwnerOfRestaurant(Long restaurantId, Long ownerId);
}
