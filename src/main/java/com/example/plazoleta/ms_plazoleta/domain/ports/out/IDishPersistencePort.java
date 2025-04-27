package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;

import java.util.Optional;

public interface IDishPersistencePort {
    Dish saveDish(Dish dish);
    Optional<Dish> findByName(String name);
}
