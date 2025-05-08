package com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.RelationValidator;

public class DishAuthorizationValidator {

    private DishAuthorizationValidator() {}

    public static void validateOwnership(Long restaurantId, Long ownerId, RestaurantPersistencePort port) {
        var restaurant = ExistenceValidator.getIfPresent(
                port.findById(restaurantId),
                ExceptionMessages.RESTAURANT_NOT_FOUND
        );

        RelationValidator.validateCondition(
                restaurant.getOwnerId().equals(ownerId),
                ExceptionMessages.NOT_OWNER_OF_RESTAURANT
        );
    }

    public static void validateDishBelongsToRestaurant(Dish dish, Long restaurantId) {
        RelationValidator.validateCondition(
                dish.getRestaurantId().equals(restaurantId),
                ExceptionMessages.DISH_WRONG_RESTAURANT
        );
    }
}