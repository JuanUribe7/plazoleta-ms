package com.example.plazoleta.ms_plazoleta.domain.usecases.order;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.order.AssignOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.OrderTraceabilityPort;

import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.RelationValidator;


public class AssignOrderUseCase implements AssignOrderServicePort {

    private final OrderPersistencePort orderPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final OrderTraceabilityPort traceabilityPort;
    public AssignOrderUseCase(OrderPersistencePort orderPersistencePort,
                              RestaurantPersistencePort restaurantPersistencePort,
                              OrderTraceabilityPort traceabilityPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.traceabilityPort = traceabilityPort;
    }

    @Override
    public void assignToOrder(Long restaurantId, Long orderId, Long employeeId) {
        Order order = ExistenceValidator.getIfPresent(
                orderPersistencePort.findById(orderId),
                ExceptionMessages.ORDER_NOT_FOUND
        );

        Restaurant restaurant = ExistenceValidator.getIfPresent(
                restaurantPersistencePort.findById(restaurantId),
                ExceptionMessages.RESTAURANT_NOT_FOUND
        );

        RelationValidator.validateCondition(
                restaurant.getEmployeeIds().contains(employeeId),
                ExceptionMessages.EMPLOYEE_NOT_ASSIGNED_TO_RESTAURANT
        );

        RelationValidator.validateCondition(
                order.getRestaurantId().equals(restaurantId),
                ExceptionMessages.ORDER_WRONG_RESTAURANT
        );

        Order updated = order.assignEmployee(employeeId, order.getId());
        traceabilityPort.assignOrder(updated);
        orderPersistencePort.save(updated);
    }
}