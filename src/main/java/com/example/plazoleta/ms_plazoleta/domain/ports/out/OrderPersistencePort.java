package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;

import java.util.List;
import java.util.Optional;

public interface OrderPersistencePort {
    Order save(Order order);
    Optional<Order> findById(Long id);
    boolean existsByClientIdAndStatuses(Long clientId, List<OrderStatus> statuses);
}
