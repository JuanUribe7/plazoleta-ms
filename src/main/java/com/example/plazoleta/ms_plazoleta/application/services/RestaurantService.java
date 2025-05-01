package com.example.plazoleta.ms_plazoleta.application.services;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PagedRestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;

public interface RestaurantService {

    RestaurantResponseDto createRestaurant(RestaurantRequestDto restaurant);

    void assignEmployee(Long restaurantId, Long employeeId, Long ownerId);
    PagedRestaurantResponseDto listRestaurants(int page, int size);
}
