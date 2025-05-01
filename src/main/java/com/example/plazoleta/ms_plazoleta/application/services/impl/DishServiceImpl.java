
package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.DishDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.DishService;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.UpdateDishServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DishServiceImpl implements DishService {

    private final CreateDishServicePort dishService;
    private final UpdateDishServicePort updateDishService;

 @Override
    public DishResponseDto createDish( DishRequestDto dto, Long restaurantId, Long ownerId) {
        Dish dish = DishDtoMapper.toModel(dto, restaurantId);
        Dish savedDish = dishService.execute(dish, ownerId);
        return DishDtoMapper.toResponseDto(savedDish);
    }

    @Override
    public DishResponseDto updateDish(UpdateDishRequestDto dto, Long dishId) {
        Dish dish=updateDishService.updateDish(dto, dishId);
        return DishDtoMapper.toResponseDto(dish);

    }

    public void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active) {
        updateDishService.changeDishStatus(dishId, restaurantId, ownerId, active);
    }



}

