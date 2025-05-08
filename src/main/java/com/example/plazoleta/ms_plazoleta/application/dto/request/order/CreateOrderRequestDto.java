package com.example.plazoleta.ms_plazoleta.application.dto.request.order;

import java.util.List;

public record CreateOrderRequestDto(
    Long restaurantId,
    List<OrderDishRequestDto> dishes
) {}
