package com.example.plazoleta.ms_plazoleta.application.services;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;

public interface DishService {
        DishResponseDto createDish(Long restaurantId, DishRequestDto dto, Long ownerId);
}
