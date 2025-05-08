package com.example.plazoleta.ms_plazoleta.application.mappers.restaurant;

import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantBasicResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

public class RestaurantSimpleDtoMapper {

    private RestaurantSimpleDtoMapper() {}

    public static RestaurantBasicResponseDto toSimpleDto(Restaurant restaurant) {
        return new RestaurantBasicResponseDto(
                restaurant.getName(),
                restaurant.getUrlLogo()
        );
    }
}