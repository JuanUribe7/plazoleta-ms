package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.UpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;
import jakarta.persistence.EntityNotFoundException;

public class UpdateDishUseCase implements UpdateDishServicePort {

    private final DishPersistencePort dishPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;

    public UpdateDishUseCase(DishPersistencePort dishPersistencePort, RestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Dish updateDish(UpdateDishRequestDto dto, Long ownerId) {
        Dish dish = dishPersistencePort.findById(dto.getDishId())
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessages.DISH_NOT_FOUND));
        dish.changeDescription(dto.getDescription());
        dish.changePrice(dto.getPrice());

        return dish.update(dishPersistencePort, restaurantPersistencePort, dto.getRestaurantId(), ownerId);
    }

    @Override
    public void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active) {
        Dish dish = dishPersistencePort.findById(dishId)
                .orElseThrow(() -> new EntityNotFoundException(ExceptionMessages.DISH_NOT_FOUND));

        Dish updated = dish.changeStatus(active, restaurantId, ownerId, restaurantPersistencePort);
        dishPersistencePort.updateDish(updated);
    }


}

