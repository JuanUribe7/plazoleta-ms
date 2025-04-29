package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IUpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IUserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.DomainValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish.DishUpdateValidator;

public class UpdateDishUseCase implements IUpdateDishServicePort {

    private final IDishPersistencePort       dishPort;
    private final DomainValidator validator;

    public UpdateDishUseCase(
            IDishPersistencePort dishPort,
            IRestaurantPersistencePort restaurantPort,
            IUserValidationPort userValidationPort
    ) {
        this.dishPort  = dishPort;
        this.validator = new DomainValidator(restaurantPort, dishPort, userValidationPort);
    }

    @Override
    public Dish updateDish(Dish dish, Long dishId) {
        // 1) Plato existe y pertenece a tu restaurante
        validator.validateDishOwnership(
                dishId,
                dish.getRestaurantId(),
                dish.getOwnerId()
        );
        // 2) Validar campos del DTO de actualización
        DishUpdateValidator.validateDish(dish);

        // 3) Asignar ID y persistir
        dish.setId(dishId);
        return dishPort.updateDish(dish);
    }

    public void changeDishStatus(Long dishId,
                                 Long restaurantId,
                                 Long ownerId,
                                 boolean active) {
        // Misma validación de existencia y propiedad
        validator.validateDishOwnership(dishId, restaurantId, ownerId);

        Dish d = dishPort.findById(dishId).get();
        d.setActive(active);
        dishPort.updateDish(d);
    }
}