package com.example.plazoleta.ms_plazoleta.domain.ports.in;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

public interface CreateDishServicePort {
    Dish execute(Dish dish, Long ownerId);
}
