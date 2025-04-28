package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.ICreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish.DishValidator;

public class CreateDishUseCase implements ICreateDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public CreateDishUseCase(IDishPersistencePort dishPersistencePort,
                             IRestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
        ;
    }

    @Override
    public Dish createDish(Dish dish){
        validate(dish);
        dish.setActive(true);

        return  dishPersistencePort.saveDish(dish);
    }

    public void validate(Dish dish) {
        DishValidator.validateDish(dish);
        Restaurant restaurante = restaurantPersistencePort.findById(dish.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("El restaurante no existe"));

        if (!restaurante.getOwnerId().equals(dish.getOwnerId())) {
            throw new IllegalArgumentException("No es propietario del restaurante.");
        }
        if (dishPersistencePort.findByName(dish.getName()).isPresent()) {
            throw new IllegalArgumentException("El nombre del plato ya está registrado.");
        }

        if (dish.getName() == null || dish.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

    }

    }







