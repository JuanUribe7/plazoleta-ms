
package com.example.plazoleta.ms_plazoleta.domain.usecases.Dish;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Dish.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;

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








