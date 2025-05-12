package com.example.plazoleta.ms_plazoleta.application.services;

import com.example.plazoleta.ms_plazoleta.application.dto.request.order.CreateOrderRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.OrderResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.model.Order;

public interface OrderService {
    Order createOrder(CreateOrderRequestDto dto, Long restaurantId, Long customerId);

    void deliverOrder(Long restaurantId, Long orderId, Long employeeId, String pin);
    void cancelOrder(Long restaurantId, Long orderId, Long clientId);
    void assignOrder(Long restaurantId, Long orderId, Long employeeId);
    void markOrderAsReady(Long restaurantId, Long orderId, Long employeeId);
    PageResponseDto<OrderResponseDto> listOrdersByStatus(Long restaurantId, String status, Long employeeId, int page, int size);
}
