package com.example.plazoleta.ms_plazoleta.application.mappers;


import com.example.plazoleta.ms_plazoleta.application.dto.request.RestaurantRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RestaurantDtoMapper {
    @Mapping(target="id", ignore = true)
    Restaurant toModel(RestaurantRequestDto restaurantRequestDto);

    RestaurantResponseDto toResponseDto(Restaurant restaurant);
    }

