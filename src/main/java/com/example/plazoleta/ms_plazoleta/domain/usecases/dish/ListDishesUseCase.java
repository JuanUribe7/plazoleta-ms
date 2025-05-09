package com.example.plazoleta.ms_plazoleta.domain.usecases.dish;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.ListDishesServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishQueryPort;

public class ListDishesUseCase implements ListDishesServicePort {

    private final DishQueryPort dishQueryPort;

    public ListDishesUseCase(DishQueryPort dishQueryPort) {
        this.dishQueryPort = dishQueryPort;
    }

    @Override
    public PaginatedResult<Dish> listDishes(Long restaurantId, String category, int page, int size) {
        return dishQueryPort.findActiveByRestaurantAndCategory(restaurantId, category, page, size);
    }
}