package com.example.plazoleta.ms_plazoleta.domain.utils.helpers;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import jakarta.persistence.EntityNotFoundException;

public class DishAuthorizationValidator {

    private DishAuthorizationValidator() {}

    public static void validateOwnership(Long restaurantId, Long ownerId, RestaurantPersistencePort port) {
        var restaurant = port.findById(restaurantId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.RESTAURANT_NOT_FOUND));

        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw new SecurityException(ExceptionMessages.NOT_OWNER_OF_RESTAURANT);
        }
    }

    public static void validateDishBelongsToRestaurant(Dish dish, Long restaurantId) {
        if (!dish.getRestaurantId().equals(restaurantId)) {
            throw new IllegalArgumentException(ExceptionMessages.DISH_WRONG_RESTAURANT);
        }
    }
}