
package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.PagedDishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.dish.DishDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.DishService;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.ListDishesServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.UpdateDishServicePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Transactional
@RequiredArgsConstructor
@Service
public class DishServiceImpl implements DishService {

    private final CreateDishServicePort dishService;
    private final UpdateDishServicePort updateDishService;
    private final ListDishesServicePort dishListService;

 @Override
    public DishResponseDto createDish(DishRequestDto dto, Long restaurantId, Long ownerId) {
        Dish dish = DishDtoMapper.toModel(dto, restaurantId);
        Dish savedDish = dishService.execute(dish, ownerId);
        return DishDtoMapper.toResponseDto(savedDish);
    }

    @Override
    public DishResponseDto updateDish(UpdateDishRequestDto dto, Long ownerId) {
        Dish dish=updateDishService.updateDish(dto, ownerId);
        return DishDtoMapper.toResponseDto(dish);

    }

    public void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active) {
        updateDishService.changeDishStatus(dishId, restaurantId, ownerId, active);
    }

    @Override
    public PageResponseDto<DishResponseDto> listDishes(Long restaurantId, String category, int page, int size) {
        return dishListService.listDishes(restaurantId, category, page, size);
    }



}

