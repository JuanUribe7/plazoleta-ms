package com.example.plazoleta.ms_plazoleta.domain.usecases.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.ListDishesServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;

import java.util.Optional;

public class ListDishesUseCase implements ListDishesServicePort {

    private final DishPersistencePort dishPort;
    private final RestaurantPersistencePort restaurantPort;

    public ListDishesUseCase(DishPersistencePort dishPort, RestaurantPersistencePort restaurantPort) {
        this.dishPort = dishPort;
        this.restaurantPort = restaurantPort;
    }

    @Override
    public PagedResult<Dish> listDishes(Long restaurantId, Pagination pagination, Optional<CategoryType> category) {
        restaurantPort.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessages.RESTAURANT_NOT_FOUND));
        return dishPort.findByRestaurantWithFilter(restaurantId, category, pagination);
    }
}
