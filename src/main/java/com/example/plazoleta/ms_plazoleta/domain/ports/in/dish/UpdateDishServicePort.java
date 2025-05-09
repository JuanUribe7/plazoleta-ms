package com.example.plazoleta.ms_plazoleta.domain.ports.in.dish;

import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

public interface UpdateDishServicePort {
    Dish updateDish(Dish dish, Long id);
    void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active);
}
