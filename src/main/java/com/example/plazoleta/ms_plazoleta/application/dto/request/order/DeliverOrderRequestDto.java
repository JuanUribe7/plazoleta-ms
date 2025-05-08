package com.example.plazoleta.ms_plazoleta.application.dto.request.order;

public record DeliverOrderRequestDto(
    Long orderId,
    String pin
) {}
