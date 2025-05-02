package com.example.plazoleta.ms_plazoleta.application.mappers.dish;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.UpdateDishResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish.CategoryValidator;


public class DishDtoMapper {

    private DishDtoMapper() {}

    public static Dish toModel(DishRequestDto dto, Long restaurantId) {
        CategoryType categoryType = CategoryValidator.validate(dto.getCategory());
        return new Dish(
                null,
                dto.getName(),
                dto.getPrice(),
                dto.getDescription(),
                dto.getImageUrl(),
                true,
                restaurantId,
                categoryType
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
