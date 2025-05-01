package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import java.util.Optional;

public interface IDishPersistencePort {
    Dish saveDish(Dish dish);
    Dish updateDish(Dish dish);
    Optional<Dish> findByName(String name);
    Optional<Dish> findById(Long id);
}
