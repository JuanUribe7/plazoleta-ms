package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.DishAuthorizationValidator;

    public class DishStatusValidator {

        private DishStatusValidator() {}

        public static void validate(Dish dish, Long restaurantId, Long ownerId,
                                    RestaurantPersistencePort restaurantPort) {
            DishAuthorizationValidator.validateOwnership(restaurantId, ownerId, restaurantPort);
            DishAuthorizationValidator.validateDishBelongsToRestaurant(dish, restaurantId);
        }
    }


