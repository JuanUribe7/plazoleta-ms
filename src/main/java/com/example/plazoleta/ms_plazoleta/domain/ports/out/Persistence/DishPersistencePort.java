package com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

import java.util.List;
import java.util.Optional;

public interface DishPersistencePort {
    Dish saveDish(Dish dish);
    Dish updateDish(Dish dish);
    long countByIdInAndRestaurantId(List<Long> dishIds, Long restaurantId);
    Optional<Dish> findByNameAndRestaurantId(String name, Long restaurantId);
    Optional<Dish> findByName(String name);
    Optional<Dish> findById(Long id);
}
