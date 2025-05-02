package com.example.plazoleta.ms_plazoleta.domain.ports.in.Order;

import com.example.plazoleta.ms_plazoleta.domain.model.Order;
import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;
import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;

import java.util.Optional;


public interface ListOrdersByStateServicePort {
    PagedResult<Order> listOrdersByState(Long restaurantId, Long ownerId, Optional<OrderStatus> status, Pagination pagination);
}
