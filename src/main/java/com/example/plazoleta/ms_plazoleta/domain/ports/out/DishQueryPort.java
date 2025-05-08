package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;

public interface DishQueryPort {
    PageResponseDto<DishResponseDto> findActiveByRestaurantAndCategory(Long restaurantId, String category, int page, int size);
}
