package com.example.plazoleta.ms_plazoleta.domain.usecases.order;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.order.DeliverOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.OrderTraceabilityPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.RelationValidator;

public class DeliverOrderUseCase implements DeliverOrderServicePort {

    private final OrderPersistencePort orderPort;
    private final RestaurantPersistencePort restaurantPort;
    private final OrderTraceabilityPort traceabilityPort;

    public DeliverOrderUseCase(
        OrderPersistencePort orderPort,
        RestaurantPersistencePort restaurantPort,
        OrderTraceabilityPort traceabilityPort
    ) {
        this.orderPort = orderPort;
        this.restaurantPort = restaurantPort;
        this.traceabilityPort = traceabilityPort;
    }

    @Override
    public Order deliver(Long restaurantId, Long orderId, Long employeeId, String pin) {
        Order order = ExistenceValidator.getIfPresent(
                orderPort.findById(orderId),
                ExceptionMessages.ORDER_NOT_FOUND
        );

        RelationValidator.validateCondition(
                order.getRestaurantId().equals(restaurantId),
                ExceptionMessages.ORDER_WRONG_RESTAURANT
        );
        Restaurant restaurant = ExistenceValidator.getIfPresent(
                restaurantPort.findById(restaurantId),
                ExceptionMessages.RESTAURANT_NOT_FOUND
        );

        RelationValidator.validateCondition(
                restaurant.getEmployeeIds().contains(employeeId),
                ExceptionMessages.EMPLOYEE_NOT_ASSIGNED_TO_RESTAURANT
        );


        traceabilityPort.deliveOrder(order, pin);
        return orderPort.save(order.markAsDelivered());
    }
}
