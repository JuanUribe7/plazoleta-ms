package com.example.plazoleta.ms_plazoleta.application.mappers;

import com.example.plazoleta.ms_plazoleta.application.dto.response.RestaurantSimpleResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantSimpleDtoMapper {
    RestaurantSimpleResponseDto toSimpleDto(Restaurant restaurant);
}
