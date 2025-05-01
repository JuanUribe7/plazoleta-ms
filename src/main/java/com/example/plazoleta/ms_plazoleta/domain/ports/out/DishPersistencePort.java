package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

import java.util.Optional;

public interface DishPersistencePort {
    Dish saveDish(Dish dish);
    Dish updateDish(Dish dish);
    Optional<Dish> findByNameAndRestaurantId(String name, Long restaurantId);
    Optional<Dish> findByName(String name);
    Optional<Dish> findById(Long id);
}
