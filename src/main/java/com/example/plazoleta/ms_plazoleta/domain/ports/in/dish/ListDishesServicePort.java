package com.example.plazoleta.ms_plazoleta.domain.ports.in.dish;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;

import java.util.Optional;

public interface ListDishesServicePort {
    PagedResult<Dish> listDishes(Long restaurantId, Pagination pagination, Optional<CategoryType> category);
}
