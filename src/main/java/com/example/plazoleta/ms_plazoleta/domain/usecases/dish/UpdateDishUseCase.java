package com.example.plazoleta.ms_plazoleta.domain.usecases.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.UpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;


public class UpdateDishUseCase implements UpdateDishServicePort {

    private final DishPersistencePort dishPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;

    public UpdateDishUseCase(DishPersistencePort dishPersistencePort, RestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Dish updateDish(Dish dish, Long ownerId) {
        Dish newDish = ExistenceValidator.getIfPresent(
                dishPersistencePort.findById(dish.getId()),
                ExceptionMessages.DISH_NOT_FOUND
        );
        newDish.changeDescription(dish.getDescription());
        newDish.changePrice(dish.getPrice());

        return newDish.update(dishPersistencePort, restaurantPersistencePort, dish.getRestaurantId(), ownerId);
    }

    @Override
    public void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active) {
        Dish dish = ExistenceValidator.getIfPresent(
                dishPersistencePort.findById(dishId),
                ExceptionMessages.DISH_NOT_FOUND
        );

        Dish updated = dish.changeStatus(active, restaurantId, ownerId, restaurantPersistencePort);
        dishPersistencePort.updateDish(updated);
    }


}

