package com.example.plazoleta.ms_plazoleta.application.services;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.UpdateDishResponseDto;

public interface DishServiceHandler {
        DishResponseDto createDish(DishRequestDto dto);
        UpdateDishResponseDto updateDish(UpdateDishRequestDto dto, Long dishId);
}
