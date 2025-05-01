
package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.PagedDishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.dish.DishDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.mappers.dish.PagedDishDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.DishService;
import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Dish.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Dish.UpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Dish.ListDishesServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class DishServiceImpl implements DishService {

    private final CreateDishServicePort dishService;
    private final UpdateDishServicePort updateDishService;
    private final ListDishesServicePort dishList;

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
    @Override
    public PagedDishResponseDto listDishes(Long restaurantId, Optional<String> category, int page, int size) {
        Pagination pagination = new Pagination(page, size);
        Optional<CategoryType> cat = category.map(CategoryType::valueOf);
        PagedResult<Dish> result = dishList.listByRestaurant(restaurantId, cat, pagination);
        return PagedDishDtoMapper.toDto(result);
    }



}

