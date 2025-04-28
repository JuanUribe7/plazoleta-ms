package com.example.plazoleta.ms_plazoleta.domain.ports.in;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

public interface IUpdateDishServicePort {
    Dish updateDish(Dish dish, Long id);
    void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active);
}
