package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.DishAuthorizationValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant.LogoValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant.NameValidator;

public class DishValidator {

    private DishValidator() {}

    public static void validateFields(Dish dish) {
        NameValidator.validate(dish.getName());
        DescriptionValidator.validate(dish.getDescription());
        LogoValidator.validate(dish.getImageUrl());

        if (dish.getPrice() <= 0) {
            throw new IllegalArgumentException(ErrorFieldsMessages.DISH_PRICE_INVALID);
        }
    }

    public static void validateDishCreation(Dish dish, Long ownerId,
                                RestaurantPersistencePort restaurantPort,
                                DishPersistencePort dishPort) {
        DishAuthorizationValidator.validateOwnership(dish.getRestaurantId(), ownerId, restaurantPort);

        dishPort.findByNameAndRestaurantId(dish.getName(), dish.getRestaurantId())
                .ifPresent(d -> {
                    throw new IllegalArgumentException(ExceptionMessages.DISH_ALREADY_EXISTS);
                });

        validateFields(dish);
    }
}
