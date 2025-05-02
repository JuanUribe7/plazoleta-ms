package com.example.plazoleta.ms_plazoleta.domain.utils.helpers;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;

public class OrderValidator {

    private OrderValidator() {}

    public static void validateAccessAndStatus(Order order, Long restaurantId, Long employeeId, RestaurantPersistencePort port) {
        if (!order.getRestaurantId().equals(restaurantId)) {
            throw new SecurityException("Order does not belong to this restaurant");
        }

        EmployeeAuthorizationValidator.validateEmployeeBelongsToRestaurant(
                restaurantId,
                employeeId,
                port
        );
    }

}