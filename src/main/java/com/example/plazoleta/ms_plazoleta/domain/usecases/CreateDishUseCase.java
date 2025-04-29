// src/main/java/com/example/plazoleta/ms_plazoleta/domain/usecases/CreateDishUseCase.java
package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.ICreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IUserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.DomainValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish.DishValidator;

public class CreateDishUseCase implements ICreateDishServicePort {

    private final IDishPersistencePort dishPort;
    private final DomainValidator validator;

    public CreateDishUseCase(
            IDishPersistencePort dishPort,
            IRestaurantPersistencePort restaurantPort,
            IUserValidationPort userValidationPort
    ) {
        this.dishPort  = dishPort;
        this.validator = new DomainValidator(restaurantPort, dishPort, userValidationPort);
    }

    @Override
    public Dish createDish(Dish dish) {
        // 1) Validar campos del DTO
        DishValidator.validateDish(dish);

        // 2) Restaurante existe y es tuyo
        validator.validateRestaurantOwnership(
                dish.getRestaurantId(),
                dish.getOwnerId()
        );

        // 3) Nombre de plato único
        validator.validateNewDishName(dish.getName());

        // 4) Activar y persistir
        dish.setActive(true);
        return dishPort.saveDish(dish);
    }
}


