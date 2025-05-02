package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.validation;

import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.OrderValidationPort;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderValidationAdapter implements OrderValidationPort {

    private final OrderRepository repository;

    public OrderValidationAdapter(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validateClientHasNoActiveOrder(Long clientId) {
        List<OrderStatus> activeStatuses = List.of(
                OrderStatus.PENDING,
                OrderStatus.IN_PREPARATION,
                OrderStatus.READY
        );

        repository.findFirstByClientIdAndStatusIn(clientId, activeStatuses)
                .ifPresent(order -> {
                    throw new IllegalStateException("Client already has an active order.");
                });
    }
}