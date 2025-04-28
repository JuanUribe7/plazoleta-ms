package com.example.plazoleta.ms_plazoleta.domain.ports.out;

public interface IUserValidationPort {
    String getRoleByUser(Long userId);
    void updateOwnerRestaurantId(Long ownerId, Long restaurantId);
}