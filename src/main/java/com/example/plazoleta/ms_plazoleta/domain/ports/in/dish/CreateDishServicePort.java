package com.example.plazoleta.ms_plazoleta.domain.ports.in.dish;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

public interface CreateDishServicePort {
    void execute(Dish dish, Long ownerId);
}
