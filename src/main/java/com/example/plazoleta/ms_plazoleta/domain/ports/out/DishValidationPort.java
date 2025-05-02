package com.example.plazoleta.ms_plazoleta.domain.ports.out;


public interface DishValidationPort {
    void validateDishesBelongToRestaurant(Long restaurantId, java.util.List<Long> dishIds);
}
