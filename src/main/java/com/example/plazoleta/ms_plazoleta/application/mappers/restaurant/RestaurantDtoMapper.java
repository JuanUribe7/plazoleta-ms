package com.example.plazoleta.ms_plazoleta.application.mappers.restaurant;


import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.CreateRestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

import java.util.ArrayList;

public class RestaurantDtoMapper {

    private RestaurantDtoMapper() {}

    public static Restaurant toModel(RestaurantRequestDto dto) {
        return new Restaurant(
                null,
                dto.name(),
                dto.nit(),
                dto.address(),
                dto.phone(),
                dto.urlLogo(),
                dto.ownerId(),
                new ArrayList<>()
        );
    }

    public static CreateRestaurantResponseDto toResponseDto(Restaurant model) {
        return new CreateRestaurantResponseDto(
                model.getId(),
                model.getName(),
                model.getAddress(),
                model.getUrlLogo()
        );
    }
}