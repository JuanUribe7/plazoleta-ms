package com.example.plazoleta.ms_plazoleta.domain.usecases.dish;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.dish.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.ListDishesServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishQueryPort;

public class ListDishesUseCase implements ListDishesServicePort {

    private final DishQueryPort dishQueryPort;

    public ListDishesUseCase(DishQueryPort dishQueryPort) {
        this.dishQueryPort = dishQueryPort;
    }

    @Override
    public PageResponseDto<DishResponseDto> listDishes(Long restaurantId, String category, int page, int size) {
        return dishQueryPort.findActiveByRestaurantAndCategory(restaurantId, category, page, size);
    }
}
