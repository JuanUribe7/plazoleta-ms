package com.example.plazoleta.ms_plazoleta.domain.usecases.Order;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.Order.CreateOrderServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.ValidationDishBelongRestaurantPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderValidationPort;

import java.util.List;

public class CreateOrderUseCase implements CreateOrderServicePort {

    private final OrderPersistencePort orderPort;
    private final OrderValidationPort orderValidationPort;
    private final ValidationDishBelongRestaurantPort validationDishBelongRestaurantPort;

    public CreateOrderUseCase(
            OrderPersistencePort orderPort,
            OrderValidationPort orderValidationPort,
            ValidationDishBelongRestaurantPort validationDishBelongRestaurantPort
    ) {
        this.orderPort = orderPort;
        this.orderValidationPort = orderValidationPort;
        this.validationDishBelongRestaurantPort = validationDishBelongRestaurantPort;
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

        validationDishBelongRestaurantPort.validateDishesBelongToRestaurant(order.getRestaurantId(), dishIds);

        // Guardar el pedido
        return orderPort.saveOrder(order);
    }
}