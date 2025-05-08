package com.example.plazoleta.ms_plazoleta.domain.ports.out.feign;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;

public interface OrderTraceabilityPort {

    void registerTrace(Order order);
    void assignOrder(Order order);

    void markAsReady(Order order, String phone);

    void deliveOrder(Order order, String pin);

    void cancelOrder(Order order);
}
