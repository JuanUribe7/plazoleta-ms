package com.example.plazoleta.ms_plazoleta.application.mappers;


import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

import java.util.ArrayList;

public class RestaurantDtoMapper {

    private RestaurantDtoMapper() {}

    public static Restaurant toModel(RestaurantRequestDto dto) {
        return new Restaurant(
                null,
                dto.getName(),
                dto.getNit(),
                dto.getAddress(),
                dto.getPhone(),
                dto.getUrlLogo(),
                dto.getOwnerId(),
                new ArrayList<>() // sin empleados al crear
        );
    }

    public static RestaurantResponseDto toResponseDto(Restaurant model) {
        return new RestaurantResponseDto(
                model.getId(),
                model.getName(),
                model.getAddress(),
                model.getUrlLogo()
        );
    }
}