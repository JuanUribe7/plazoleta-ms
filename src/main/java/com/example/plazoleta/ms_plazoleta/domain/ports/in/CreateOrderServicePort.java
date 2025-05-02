package com.example.plazoleta.ms_plazoleta.domain.ports.in;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;

public interface CreateOrderServicePort {
    Order createOrder(Order order);
}