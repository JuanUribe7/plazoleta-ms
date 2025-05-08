package com.example.plazoleta.ms_plazoleta.domain.utils.validation.relation;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.NotFoundException;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;

import java.util.List;

public class DishBelongToRestaurantValidator {
    private DishBelongToRestaurantValidator() {
        throw new UnsupportedOperationException(ExceptionMessages.UTILITY_CLASS_INSTANTIATION);
    }
    public static void validateDishIdsBelongToRestaurant(Long restaurantId, List<Long> dishIds, DishPersistencePort port) {
        long count = port.countByIdInAndRestaurantId(dishIds, restaurantId);
        if (count != dishIds.size()) {
            throw new NotFoundException(ExceptionMessages.DISH_WRONG_RESTAURANT);
        }
    }
}
