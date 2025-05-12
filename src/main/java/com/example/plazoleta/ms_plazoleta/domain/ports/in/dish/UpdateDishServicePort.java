package com.example.plazoleta.ms_plazoleta.domain.ports.in.dish;


import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

public interface UpdateDishServicePort {
    void updateDish(Dish dish, Long ownerId,Long restaurantId);
    void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active);
}
