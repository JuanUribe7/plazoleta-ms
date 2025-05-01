package com.example.plazoleta.ms_plazoleta.application.mappers;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.UpdateDishResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;


public class DishDtoMapper {

    private DishDtoMapper() {}

    public static Dish toModel(DishRequestDto dto, Long restaurantId) {
        return new Dish(
                null,
                dto.getName(),
                dto.getPrice(),
                dto.getDescription(),
                dto.getImageUrl(),
                true,
                restaurantId,
                dto.getCategory()
        );
    }



    public static DishResponseDto toResponseDto(Dish model) {
        return new DishResponseDto(
                model.getId(),
                model.getName(),
                model.getPrice(),
                model.getDescription(),
                model.getImageUrl(),
                model.getCategory().name(),
                model.getRestaurantId(),
                model.isActive()
        );
    }
    public static UpdateDishResponseDto toUpdResponseDto(Dish model) {
        return new UpdateDishResponseDto(
                model.getId(),
                model.getName(),
                model.getPrice(),
                model.getDescription()
        );
    }
}
