package com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.DishAuthorizationValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.DescriptionValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.DishFieldValidator;
import jakarta.persistence.EntityNotFoundException;

public class DishUpdateValidator {

        private DishUpdateValidator() {
            throw new UnsupportedOperationException("Utility class");
        }

        public static void validate(Dish dish, Long dishId, Long ownerId,
                                    DishPersistencePort dishPort,
                                    RestaurantPersistencePort restaurantPort) {

            Dish original = ExistenceValidator.getIfPresent(
                    dishPort.findById(dishId),
                    ExceptionMessages.DISH_NOT_FOUND
            );

            DishAuthorizationValidator.validateOwnership(original.getRestaurantId(), ownerId, restaurantPort);
            DishAuthorizationValidator.validateDishBelongsToRestaurant(dish, original.getRestaurantId());

            DishFieldValidator.validatePrice(dish.getPrice());
            DescriptionValidator.validate(dish.getDescription());
        }
    }
