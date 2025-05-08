package com.example.plazoleta.ms_plazoleta.domain.usecases.order;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.order.DeliverOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.OrderTraceabilityPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.OrderValidator;

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
        OrderValidator.validateAccessAndStatus(order, restaurantId, employeeId, restaurantPort);

        traceabilityPort.deliveOrder(order, pin);
        return orderPort.save(order.markAsDelivered());
    }
}
