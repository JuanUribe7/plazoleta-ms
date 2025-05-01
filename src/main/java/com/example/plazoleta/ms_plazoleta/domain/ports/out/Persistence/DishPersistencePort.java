package com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;

import java.util.Optional;

public interface DishPersistencePort {
    Dish saveDish(Dish dish);
    Dish updateDish(Dish dish);
    PagedResult<Dish> findByRestaurantWithFilter(Long restaurantId, Optional<CategoryType> category, Pagination pagination);
    Optional<Dish> findByNameAndRestaurantId(String name, Long restaurantId);
    Optional<Dish> findByName(String name);
    Optional<Dish> findById(Long id);
}
