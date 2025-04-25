package com.example.plazoleta.ms_plazoleta.domain.ports.out;

import com.example.plazoleta.ms_plazoleta.infrastructure.dto.UserResponseDto;

public interface IUserClientPort {

    UserResponseDto getUserById(Long id);
    void assignRestaurantToOwner(Long userId, Long restaurantId);

}
