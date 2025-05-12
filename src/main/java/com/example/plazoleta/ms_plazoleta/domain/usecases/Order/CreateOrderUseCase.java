package com.example.plazoleta.ms_plazoleta.domain.usecases.order;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.AlreadyExistsException;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.NotFoundException;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderDish;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.order.CreateOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.OrderTraceabilityPort;

import java.util.List;

public class CreateOrderUseCase implements CreateOrderServicePort {

    private final OrderPersistencePort orderPort;
    private final DishPersistencePort dishPort;
    private final OrderTraceabilityPort traceabilityPort;


    public CreateOrderUseCase(
            OrderPersistencePort orderPort,
            DishPersistencePort dishPort,
            OrderTraceabilityPort traceabilityPort
    ) {
        this.orderPort = orderPort;
        this.dishPort = dishPort;
        this.traceabilityPort = traceabilityPort;
    }

    @Override
    public Order createOrder(Order order) {
        final List<OrderStatus> ACTIVE_STATUSES = List.of(
                OrderStatus.PENDING,
                OrderStatus.PREPARING,
                OrderStatus.READY
        );

        if (orderPort.existsByClientIdAndStatuses(order.getClientId(), ACTIVE_STATUSES)) {
            throw new AlreadyExistsException(ExceptionMessages.CLIENT_ALREADY_HAVE_ORDER);
        }

        List<Long> dishIds = order.getDishes()
                .stream()
                .map(OrderDish::getDishId)
                .toList();


        long count = dishPort.countByIdInAndRestaurantId(dishIds, order.getRestaurantId());
        if (count != dishIds.size()) {
            throw new NotFoundException(ExceptionMessages.DISH_WRONG_RESTAURANT);
        }
        Order savedOrder = orderPort.save(order);
        traceabilityPort.registerTrace(savedOrder);
        return savedOrder;
    }
}
