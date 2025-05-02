package com.example.plazoleta.ms_plazoleta.domain.usecases.Order;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Order.AssignOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.EmployeeAuthorizationValidator;

public class AssignOrderUseCase implements AssignOrderServicePort {

    private final OrderPersistencePort orderPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;

    public AssignOrderUseCase(OrderPersistencePort orderPersistencePort, RestaurantPersistencePort restaurantPersistencePort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Order assignToOrder(Long restaurantId, Long orderId, Long employeeId) {
        Order order = orderPersistencePort.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));

        EmployeeAuthorizationValidator.validateEmployeeBelongsToRestaurant(
                restaurantId,
                employeeId,
                restaurantPersistencePort
        );

        if (!order.getRestaurantId().equals(restaurantId)) {
            throw new SecurityException("Order does not belong to this restaurant.");
        }

        Order updated = order.assignEmployee(employeeId, order.getId());
        return orderPersistencePort.saveOrder(updated);
    }
}