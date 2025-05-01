
package com.example.plazoleta.ms_plazoleta.domain.usecases.create;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;

public class CreateDishUseCase implements CreateDishServicePort {

    private final DishPersistencePort dishPort;
    private final RestaurantPersistencePort restaurantPort;

    public CreateDishUseCase(DishPersistencePort dishPersistencePort,
                             RestaurantPersistencePort restaurantPersistencePort) {
        this.dishPort = dishPersistencePort;
        this.restaurantPort = restaurantPersistencePort;
    }

    @Override
    public Dish execute(Dish dish, Long ownerId) {
        return dish.create(restaurantPort, dishPort, ownerId);
    }

    }








