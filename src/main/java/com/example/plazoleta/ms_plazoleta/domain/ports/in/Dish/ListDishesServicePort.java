package com.example.plazoleta.ms_plazoleta.domain.ports.in.Dish;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;

import java.util.Optional;

public interface ListDishesServicePort {
    PagedResult<Dish> listByRestaurant(Long restaurantId, Optional<CategoryType> category, Pagination pagination);
}
