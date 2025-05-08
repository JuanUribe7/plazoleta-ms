package com.example.plazoleta.ms_plazoleta.application.dto.request.trace;

import jakarta.validation.constraints.NotNull;

public record OrderTraceabilityRequest(
    @NotNull Long orderId,
    @NotNull Long clientId
) {}
