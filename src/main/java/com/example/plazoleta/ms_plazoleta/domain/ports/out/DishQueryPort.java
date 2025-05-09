package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;

public interface DishQueryPort {
    PaginatedResult<Dish> findActiveByRestaurantAndCategory(Long restaurantId, String category, int page, int size);
}
