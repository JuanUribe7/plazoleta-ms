package com.example.plazoleta.ms_plazoleta.domain.usecases.order;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.order.CancelOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;


import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.OrderTraceabilityPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;

import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.RelationValidator;

public class CancelOrderUseCase implements CancelOrderServicePort {

    private final OrderPersistencePort orderPort;
    private final OrderTraceabilityPort traceabilityPort;

    public CancelOrderUseCase(
            OrderPersistencePort orderPort,
            OrderTraceabilityPort traceabilityPort
    ) {
        this.orderPort = orderPort;

        this.traceabilityPort = traceabilityPort;
    }

    @Override
    public void cancel(Long restaurantId, Long orderId, Long clientId) {
        Order order = ExistenceValidator.getIfPresent(
                orderPort.findById(orderId),
                ExceptionMessages.ORDER_NOT_FOUND
        );
        RelationValidator.validateCondition(order.getRestaurantId().equals(restaurantId),ExceptionMessages.ORDER_WRONG_RESTAURANT);

        traceabilityPort.cancelOrder(order);

         orderPort.save(order.markAsCancelled());
    }



}
