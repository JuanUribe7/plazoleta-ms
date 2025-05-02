package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.validation;


import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishValidationPort;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.DishRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DishValidationAdapter implements DishValidationPort {

    private final DishRepository repository;

    public DishValidationAdapter(DishRepository repository) {
        this.repository = repository;
    }

    @Override
    public void validateDishesBelongToRestaurant(Long restaurantId, List<Long> dishIds) {
        long count = repository.countByIdInAndRestaurantId(dishIds, restaurantId);
        if (count != dishIds.size()) {
            throw new IllegalArgumentException("Some dishes do not belong to the specified restaurant.");
        }
    }
}
