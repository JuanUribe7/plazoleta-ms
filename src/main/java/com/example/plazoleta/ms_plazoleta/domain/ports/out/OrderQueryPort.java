package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;

public interface OrderQueryPort {
    PaginatedResult<Order> findAllByStatusAndRestaurant(String status, Long restaurantId, int page, int size);
}
