package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.DishAuthorizationValidator;
import jakarta.persistence.EntityNotFoundException;

public class DishUpdateValidator {

    private DishUpdateValidator() {}

    public static void validate(Dish dish, Long dishId, Long ownerId,
                                DishPersistencePort dishPort,
                                RestaurantPersistencePort restaurantPort) {
        Dish original = dishPort.findById(dishId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.DISH_NOT_FOUND));

        DishAuthorizationValidator.validateOwnership(original.getRestaurantId(), ownerId, restaurantPort);
        DishAuthorizationValidator.validateDishBelongsToRestaurant(dish, original.getRestaurantId());

        if (dish.getPrice() == null || dish.getPrice() <= 0) {
            throw new IllegalArgumentException(ErrorFieldsMessages.DISH_INVALID_PRICE);
        }
        DescriptionValidator.validate(dish.getDescription());
    }

}
