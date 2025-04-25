package com.example.plazoleta.ms_plazoleta.application.services;

import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;

public interface RestaurantService {

    RestaurantResponseDto saveRestaurant(RestaurantRequestDto restaurant);
}
