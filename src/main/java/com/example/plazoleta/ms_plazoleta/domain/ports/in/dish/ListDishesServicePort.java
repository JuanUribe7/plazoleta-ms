package com.example.plazoleta.ms_plazoleta.domain.ports.in.dish;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;

public interface ListDishesServicePort {
    PaginatedResult<Dish> listDishes(Long restaurantId, String category, int page, int size);
}
