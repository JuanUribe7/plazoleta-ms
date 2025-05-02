package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;

public interface OrderPersistencePort {
    Order saveOrder(Order order);
}
