package com.example.plazoleta.ms_plazoleta.application.mappers.dish;



import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.CategoryValidator;

import java.util.List;


public class DishDtoMapper {

    private DishDtoMapper() {}

    public static Dish toModel(DishRequestDto dto, Long restaurantId) {
        CategoryType categoryType = CategoryValidator.validate(dto.category());
        return new Dish(
                null,
                dto.name(),
                dto.price(),
                dto.description(),
                dto.imageUrl(),
                true,
                restaurantId,
                categoryType
        );
    }

    public static Dish toModel(UpdateDishRequestDto dto, Dish existingDish) {
        return new Dish(
                dto.getDishId(),
                existingDish.getName(),
                dto.getPrice() != null ? dto.getPrice() : existingDish.getPrice(),
                dto.getDescription() != null ? dto.getDescription() : existingDish.getDescription(),
                existingDish.getUrlImage(),
                existingDish.isAvailable(),
                existingDish.getRestaurantId(),
                existingDish.getCategory()
        );
    }



    public static DishResponseDto toResponseDto(Dish model) {
        return new DishResponseDto(
                model.getId(),
                model.getName(),
                model.getPrice(),
                model.getDescription(),
                model.getUrlImage(),
                model.getCategory().name()
        );

    }
    public static List<DishResponseDto> toResponseDtoList(List<Dish> dishes) {
        return dishes.stream()
                .map(DishDtoMapper::toResponseDto)
                .toList();
    }


}
