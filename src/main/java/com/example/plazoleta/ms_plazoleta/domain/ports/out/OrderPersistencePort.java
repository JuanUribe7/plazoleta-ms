package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;

import java.util.Optional;

public interface OrderPersistencePort {
    Order saveOrder(Order order);
    Optional<Order> findById(Long id);
}
