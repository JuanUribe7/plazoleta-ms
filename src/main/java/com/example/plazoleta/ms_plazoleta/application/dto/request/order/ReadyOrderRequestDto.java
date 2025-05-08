package com.example.plazoleta.ms_plazoleta.application.dto.request.order;

public record ReadyOrderRequestDto(
    Long orderId,
    String phoneNumber
) {}
