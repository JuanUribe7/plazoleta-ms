
package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.dish.DishDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.DishService;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.ListDishesServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.UpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Transactional
@RequiredArgsConstructor
@Service
public class DishServiceImpl implements DishService {

    private final CreateDishServicePort dishService;
    private final UpdateDishServicePort updateDishService;
    private final ListDishesServicePort dishListService;
    private final DishPersistencePort dishPersistencePort;

 @Override
    public void createDish(DishRequestDto dto, Long restaurantId, Long ownerId) {
        Dish dish = DishDtoMapper.toModel(dto, restaurantId);
       dishService.execute(dish, ownerId);

    }

    @Override
    public void updateDish(UpdateDishRequestDto dto, Long ownerId) {
        Dish existingDish = ExistenceValidator.getIfPresent(
                dishPersistencePort.findById(dto.getDishId()),
                ExceptionMessages.DISH_NOT_FOUND
        );
        Dish dish = DishDtoMapper.toModel(dto, existingDish);
        updateDishService.updateDish(dish, ownerId, dto.getRestaurantId());

    }

    public void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active) {
        updateDishService.changeDishStatus(dishId, restaurantId, ownerId, active);
    }


    @Override
    public PageResponseDto<DishResponseDto> listDishes(Long restaurantId, String category, int page, int size) {
        PaginatedResult<Dish> paginatedResult = dishListService.listDishes(restaurantId, category, page, size);

        List<DishResponseDto> content = paginatedResult.getContent().stream()
                .map(DishDtoMapper::toResponseDto)
                .toList();

        return new PageResponseDto<>(
                content,
                paginatedResult.getPage(),
                paginatedResult.getSize(),
                paginatedResult.getTotalElements(),
                paginatedResult.getTotalPages(),
                page == 0,
                page == paginatedResult.getTotalPages() - 1,
                page < paginatedResult.getTotalPages() - 1,
                page > 0
        );
    }
}




