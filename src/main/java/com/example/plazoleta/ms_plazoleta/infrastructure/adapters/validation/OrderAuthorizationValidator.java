package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.validation;

import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import org.springframework.stereotype.Component;

@Component
public class OrderAuthorizationValidator {

    private OrderAuthorizationValidator() {}

    public static void validateEmployeeAssignment(
            Order order,
            Long employeeId,
            RestaurantPersistencePort restaurantPort
    ) {
        Restaurant restaurant = restaurantPort.findById(order.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurant not found."));

        if (!restaurant.getEmployeeIds().contains(employeeId)) {
            throw new SecurityException("Employee is not allowed to assign this order.");
        }

        if (!OrderStatus.PENDING.equals(order.getStatus())) {
            throw new IllegalStateException("Only orders in PENDING status can be assigned.");
        }
    }
}