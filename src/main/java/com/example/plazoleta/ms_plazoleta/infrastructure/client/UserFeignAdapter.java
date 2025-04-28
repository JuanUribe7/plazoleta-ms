package com.example.plazoleta.ms_plazoleta.infrastructure.client;

import com.example.plazoleta.ms_plazoleta.domain.ports.out.IUserValidationPort;
import org.springframework.stereotype.Component;

@Component
public class UserFeignAdapter implements IUserValidationPort {

    private final UserFeignClient userFeignClient;

    public UserFeignAdapter(UserFeignClient userFeignClient) {
        this.userFeignClient = userFeignClient;
    }

    @Override
    public String getRoleByUser(Long userId) {
        return userFeignClient.getRoleByUser(userId);
    }

    @Override
    public void updateOwnerRestaurantId(Long ownerId, Long restaurantId) {
        userFeignClient.updateOwnerRestaurant(ownerId, restaurantId);
    }
}
