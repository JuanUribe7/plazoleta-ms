package com.example.plazoleta.ms_plazoleta.application.dto.response;


import com.example.plazoleta.ms_plazoleta.domain.model.OrderStatus;

import java.util.List;

public record OrderResponseDto(
        Long id,
        Long clientId,
        Long restaurantId,
        OrderStatus state,
        Long employeeId,
        List<DishOrderResponseDto> dishes
) {}