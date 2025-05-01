package com.example.plazoleta.ms_plazoleta.application.services;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.PagedDishResponseDto;

import java.util.Optional;

public interface DishService {
        DishResponseDto createDish(DishRequestDto dto, Long restaurantId, Long ownerId);
        DishResponseDto updateDish(UpdateDishRequestDto dto, Long dishId);
        void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active);
        PagedDishResponseDto listDishes(Long restaurantId, Optional<String> category, int page, int size);
}
