package com.example.plazoleta.ms_plazoleta.domain.usecases.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.DishValidationFields;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.UpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.services.ValidationFieldsService;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.DishAuthorizationValidator;


public class UpdateDishUseCase implements UpdateDishServicePort {

    private final DishPersistencePort dishPersistencePort;
    private final RestaurantPersistencePort restaurantPersistencePort;
    private final DishValidationFields validationFieldsService;

    public UpdateDishUseCase(DishPersistencePort dishPersistencePort,
                             DishValidationFields validationFieldsService,
                             RestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.validationFieldsService = validationFieldsService;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public void updateDish(Dish dish, Long ownerId, Long restaurantId) {
        ExistenceValidator.getIfPresent(
                dishPersistencePort.findById(dish.getId()),
                ExceptionMessages.DISH_NOT_FOUND
        );
        validationFieldsService.validateDish(dish);
        dish.changeDescription(dish.getDescription());
        dish.changePrice(dish.getPrice());

        DishAuthorizationValidator.validateOwnership(dish.getRestaurantId(), ownerId, restaurantPersistencePort);
        DishAuthorizationValidator.validateDishBelongsToRestaurant(dish, restaurantId);
        dishPersistencePort.updateDish(dish);
    }

    @Override
    public void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active) {
        Dish dish = ExistenceValidator.getIfPresent(
                dishPersistencePort.findById(dishId),
                ExceptionMessages.DISH_NOT_FOUND
        );

        DishAuthorizationValidator.validateOwnership(dish.getRestaurantId(), ownerId, restaurantPersistencePort);
        DishAuthorizationValidator.validateDishBelongsToRestaurant(dish, dish.getRestaurantId());
        dishPersistencePort.updateDish(dish.changeStatus(active));
    }


}

