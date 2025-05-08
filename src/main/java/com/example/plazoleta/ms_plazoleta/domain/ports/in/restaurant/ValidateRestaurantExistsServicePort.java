package com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant;

import java.util.List;

public interface ValidateRestaurantExistsServicePort {
    void validate(Long restaurantId);

    List<Long> getEmployeeIdsByOwner(Long ownerId);
}
