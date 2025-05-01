package com.example.plazoleta.ms_plazoleta.application.mappers.dish;

import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishSimpleResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

public class DishSimpleDtoMapper {
    public static DishSimpleResponseDto toDto(Dish dish) {

        System.out.println("[MAPPER] name=" + dish.getName() +
                ", price=" + dish.getPrice() +
                ", desc=" + dish.getDescription());
        return new DishSimpleResponseDto(
                dish.getName(),
                dish.getPrice(),
                dish.getDescription()
        );
    }
}
