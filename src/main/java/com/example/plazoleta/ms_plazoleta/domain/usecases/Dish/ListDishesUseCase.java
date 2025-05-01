package com.example.plazoleta.ms_plazoleta.domain.usecases.Dish;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Dish.ListDishesServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;

import java.util.Optional;

public class ListDishesUseCase implements ListDishesServicePort {

    private final DishPersistencePort port;

    public ListDishesUseCase(DishPersistencePort port) {
        this.port = port;
    }

    @Override
    public PagedResult<Dish> listByRestaurant(Long restaurantId, Optional<CategoryType> category, Pagination pagination) {
        return port.findByRestaurantWithFilter(restaurantId, category, pagination);
    }
}
