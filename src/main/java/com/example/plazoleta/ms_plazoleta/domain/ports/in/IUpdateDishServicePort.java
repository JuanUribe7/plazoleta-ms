package com.example.plazoleta.ms_plazoleta.domain.ports.in;

public interface IUpdateDishServicePort {
    Dish updateDish(Dish dish, Long id);
    void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active);
}
