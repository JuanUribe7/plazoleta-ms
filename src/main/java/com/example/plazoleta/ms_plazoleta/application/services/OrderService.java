package com.example.plazoleta.ms_plazoleta.application.services;

import com.example.plazoleta.ms_plazoleta.application.dto.request.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.OrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PagedOrderResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;

public interface OrderService {
    Order createOrder(Long restaurantId, Long clientId, CreateOrderRequestDto dto);
    PagedOrderResponseDto listOrdersByState(Long restaurantId, Long employeeId, String status, int page, int size);
    OrderResponseDto assignOrder(Long restaurantId, Long orderId, Long employeeId);
    OrderResponseDto markOrderAsReady(Long restaurantId, Long orderId, Long employeeId);
}
