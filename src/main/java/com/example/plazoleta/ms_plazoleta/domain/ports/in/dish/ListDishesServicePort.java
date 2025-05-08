package com.example.plazoleta.ms_plazoleta.domain.ports.in.dish;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;

public interface ListDishesServicePort {
    PageResponseDto<DishResponseDto> listDishes(Long restaurantId, String category, int page, int size);
}
