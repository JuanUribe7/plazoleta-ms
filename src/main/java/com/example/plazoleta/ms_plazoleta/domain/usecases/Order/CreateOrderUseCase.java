package com.example.plazoleta.ms_plazoleta.domain.usecases.order;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderDish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.order.CreateOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.feign.OrderTraceabilityPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.relation.DishBelongToRestaurantValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.order.OrderDomainValidator;

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

        OrderDomainValidator.validateClientHasNoActiveOrder(order.getClientId(), orderPort);


        List<Long> dishIds = order.getDishes()
                .stream()
                .map(OrderDish::getDishId)
                .toList();

        DishBelongToRestaurantValidator.validateDishIdsBelongToRestaurant(order.getRestaurantId(), dishIds, dishPort);

        Order savedOrder = orderPort.save(order);
        traceabilityPort.registerTrace(savedOrder);
        return savedOrder;
    }
}
