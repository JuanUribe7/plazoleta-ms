package com.example.plazoleta.ms_plazoleta.domain.ports.out;

public interface OrderValidationPort {
    void validateClientHasNoActiveOrder(Long clientId);
}
