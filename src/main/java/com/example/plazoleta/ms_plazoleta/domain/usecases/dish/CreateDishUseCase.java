
package com.example.plazoleta.ms_plazoleta.domain.usecases.dish;

import com.example.plazoleta.ms_plazoleta.commons.constants.ErrorFieldsMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.commons.constants.FieldNames;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.AlreadyExistsException;
import com.example.plazoleta.ms_plazoleta.commons.exceptions.InvalidFieldException;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.CreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.services.ValidationFieldsService;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.ExistenceValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.RelationValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.helpers.UniquenessValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.CategoryValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.create.dish.DishAuthorizationValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish.DishCreationValidator;

public class CreateDishUseCase implements CreateDishServicePort {

    private final DishPersistencePort dishPort;
    private final RestaurantPersistencePort restaurantPort;
    private final ValidationFieldsService validationFieldsService;

    public CreateDishUseCase(DishPersistencePort dishPersistencePort,
                             RestaurantPersistencePort restaurantPersistencePort,
                             ValidationFieldsService validationFieldsService) {
        this.dishPort = dishPersistencePort;
        this.restaurantPort = restaurantPersistencePort;
        this.validationFieldsService = validationFieldsService;
    }

    @Override
    public void execute(Dish dish, Long ownerId) {

        DishCreationValidator.validate(dish, ownerId, restaurantPort, dishPort);
        validationFieldsService.validateLogo(dish.getUrlImage());
        validationFieldsService.validateName(dish.getName());
        validationFieldsService.validateDescription(dish.getDescription());
        if (dish.getPrice() == null || dish.getPrice().doubleValue() <= 0) {
            throw new InvalidFieldException(ErrorFieldsMessages.DISH_PRICE_INVALID);
        }
        DishAuthorizationValidator.validateOwnership(dish.getRestaurantId(), ownerId, restaurantPort);
        DishAuthorizationValidator.validateDishBelongsToRestaurant(dish, dish.getRestaurantId());

        dishPort.findByNameAndRestaurantId(dish.getName(), dish.getRestaurantId())
                .ifPresent(d -> {
                    throw new AlreadyExistsException(ExceptionMessages.DISH_ALREADY_EXISTS);
                });

        UniquenessValidator.validate(() -> dishPort.existsByName(dish.getName()), FieldNames.NAME, dish.getName());
        UniquenessValidator.validate(() -> dishPort.existsByUrlImage(dish.getUrlImage()), FieldNames.IMAGE, dish.getName());

        dishPort.saveDish(dish);

    }

    }








