package com.example.plazoleta.ms_plazoleta.application.mappers.restaurant;

import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantSimpleResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

public class RestaurantSimpleDtoMapper {

    private RestaurantSimpleDtoMapper() {}

    public static RestaurantSimpleResponseDto toSimpleDto(Restaurant restaurant) {
        return new RestaurantSimpleResponseDto(
                restaurant.getName(),
                restaurant.getUrlLogo()
        );
    }
}