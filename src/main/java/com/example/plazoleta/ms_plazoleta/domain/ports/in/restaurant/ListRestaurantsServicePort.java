package com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantBasicResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

public interface ListRestaurantsServicePort {
    PaginatedResult<Restaurant> listRestaurants(int page, int size);
}
