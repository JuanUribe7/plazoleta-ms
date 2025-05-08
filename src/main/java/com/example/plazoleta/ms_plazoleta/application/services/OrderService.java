package com.example.plazoleta.ms_plazoleta.application.services;

import com.example.plazoleta.ms_plazoleta.application.dto.request.order.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.OrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;

public interface OrderService {
    Order createOrder(CreateOrderRequestDto dto, Long restaurantId, Long customerId);

    OrderResponseDto deliverOrder(Long restaurantId, Long orderId, Long employeeId, String pin);
    OrderResponseDto cancelOrder(Long restaurantId, Long orderId, Long clientId);
    OrderResponseDto assignOrder(Long restaurantId, Long orderId, Long employeeId);
    OrderResponseDto markOrderAsReady(Long restaurantId, Long orderId, Long employeeId);
    PageResponseDto<OrderResponseDto> listOrdersByStatus(Long restaurantId, String status, Long employeeId, int page, int size);
}
