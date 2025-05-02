package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;

import java.util.Optional;


public interface OrderQueryPort {
    PagedResult<Order> findByRestaurantAndStatus(Long restaurantId, Optional<OrderStatus> status, Pagination pagination);
}
