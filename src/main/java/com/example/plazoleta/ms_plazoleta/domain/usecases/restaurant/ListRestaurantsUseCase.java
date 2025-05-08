package com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantBasicResponseDto;

import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.ListRestaurantsServicePort;

import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantQueryPort;

public class ListRestaurantsUseCase implements ListRestaurantsServicePort {

    private final RestaurantQueryPort restaurantQueryPort;

    public ListRestaurantsUseCase(RestaurantQueryPort restaurantQueryPort) {
        this.restaurantQueryPort = restaurantQueryPort;
    }

    @Override
    public PageResponseDto<RestaurantBasicResponseDto> listRestaurants(int page, int size) {
        return restaurantQueryPort.findAllSortedByName(page, size);
    }
}
