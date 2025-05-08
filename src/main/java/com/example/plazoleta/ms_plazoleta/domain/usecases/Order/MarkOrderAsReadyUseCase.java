package com.example.plazoleta.ms_plazoleta.domain.usecases.order;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.order.MarkOrderAsReadyServicePort;


import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.OrderTraceabilityPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.OrderValidator;



public class MarkOrderAsReadyUseCase implements MarkOrderAsReadyServicePort {

    private final OrderPersistencePort orderPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;

    private final UserValidationPort userValidationPort;
    private final OrderTraceabilityPort orderTraceabilityPort;

    public MarkOrderAsReadyUseCase(OrderPersistencePort orderPersistencePort,
                                   RestaurantPersistencePort restaurantPersistencePort,
                                   UserValidationPort userValidationPort,
                                   OrderTraceabilityPort orderTraceabilityPort) {
        this.orderPersistencePort = orderPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userValidationPort = userValidationPort;

        this.orderTraceabilityPort = orderTraceabilityPort;
    }

    @Override
    public Order markAsReady(Long restaurantId, Long orderId, Long employeeId) {
        Order order = ExistenceValidator.getIfPresent(
                orderPersistencePort.findById(orderId),
                ExceptionMessages.ORDER_NOT_FOUND
        );
        OrderValidator.validateAccessAndStatus(order, restaurantId, employeeId, restaurantPersistencePort);

        String phoneNumber = userValidationPort.getPhoneByUserId(order.getClientId());


        orderTraceabilityPort.markAsReady(order, phoneNumber);
        return orderPersistencePort.save(order.markAsReady());
    }
}