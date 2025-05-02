package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.CreateOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderValidationPort;

import java.util.List;

public class CreateOrderUseCase implements CreateOrderServicePort {

    private final OrderPersistencePort orderPort;
    private final OrderValidationPort orderValidationPort;
    private final DishValidationPort dishValidationPort;

    public CreateOrderUseCase(
            OrderPersistencePort orderPort,
            OrderValidationPort orderValidationPort,
            DishValidationPort dishValidationPort
    ) {
        this.orderPort = orderPort;
        this.orderValidationPort = orderValidationPort;
        this.dishValidationPort = dishValidationPort;
    }

    @Override
    public Order createOrder(Order order) {
        // Validar que el cliente no tenga otro pedido activo
        orderValidationPort.validateClientHasNoActiveOrder(order.getClientId());

        // Validar que todos los platos existan y pertenezcan al restaurante
        List<Long> dishIds = order.getDishes()
                .stream()
                .map(d -> d.getDishId())
                .toList();

        dishValidationPort.validateDishesBelongToRestaurant(order.getRestaurantId(), dishIds);

        // Guardar el pedido
        return orderPort.saveOrder(order);
    }
}