package com.example.plazoleta.ms_plazoleta.application.services;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;

import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantBasicResponseDto;

import java.util.List;

public interface RestaurantService {

    void createRestaurant(RestaurantRequestDto restaurant);
    void validateExists(Long restaurantId);
    void assignEmployee(Long restaurantId, Long employeeId, Long ownerId);
    PageResponseDto<RestaurantBasicResponseDto> listRestaurants(int page, int size);

    List<Long> getEmployeeIdsByOwner(Long ownerId);
}
