package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.AlreadyExistsException;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.DishAuthorizationValidator;

public class DishCreationValidator {
    public static void validate(Dish dish, Long ownerId,
                                RestaurantPersistencePort restaurantPort,
                                DishPersistencePort dishPort) {

        DishAuthorizationValidator.validateOwnership(dish.getRestaurantId(), ownerId, restaurantPort);

        dishPort.findByNameAndRestaurantId(dish.getName(), dish.getRestaurantId())
                .ifPresent(d -> {
                    throw new AlreadyExistsException(ExceptionMessages.DISH_ALREADY_EXISTS);
                });
    }
}
