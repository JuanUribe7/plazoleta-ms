package com.example.plazoleta.ms_plazoleta.domain.ports.in.order;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;

public interface CancelOrderServicePort {
    void cancel(Long restaurantId, Long orderId, Long clientId);
}
