package com.example.plazoleta.ms_plazoleta.domain.utils.helpers;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.existenceandrelation.EmployeeAuthorizationValidator;

public class OrderValidator {

    private OrderValidator() {}

    public static void validateAccessAndStatus(Order order, Long restaurantId, Long employeeId, RestaurantPersistencePort port) {
        RelationValidator.validateCondition(
                order.getRestaurantId().equals(restaurantId),
                ExceptionMessages.ORDER_WRONG_RESTAURANT
        );

        EmployeeAuthorizationValidator.validateEmployeeBelongsToRestaurant(
                restaurantId,
                employeeId,
                port
        );
    }

}