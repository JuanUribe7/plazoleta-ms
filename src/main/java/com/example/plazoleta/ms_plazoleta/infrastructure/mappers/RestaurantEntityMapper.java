package com.example.plazoleta.ms_plazoleta.infrastructure.mappers;


import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.*;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestaurantEntityMapper {
    Restaurant toModel(RestaurantEntity restaurantEntity);
    RestaurantEntity toEntity(Restaurant restaurant);
    List<Restaurant> toModelList(List<RestaurantEntity> entities);
}
