package com.example.plazoleta.ms_plazoleta.domain.ports.in.order;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;

public interface ListOrdersByStatusServicePort {
    PaginatedResult<Order> findByStatusAndRestaurant(String status, Long restaurantId, Long employeeId, int page, int size);
}
