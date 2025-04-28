package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IUpdateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.dish.DishUpdateValidator;

public class UpdateDishUseCase implements IUpdateDishServicePort {

    private final IDishPersistencePort dishPersistencePort;
    private final IRestaurantPersistencePort restaurantPersistencePort;

    public UpdateDishUseCase(IDishPersistencePort dishPersistencePort, IRestaurantPersistencePort restaurantPersistencePort) {
        this.dishPersistencePort = dishPersistencePort;
        this.restaurantPersistencePort = restaurantPersistencePort;
    }

    @Override
    public Dish updateDish(Dish dish, Long dishId) {
        dish.setId(dishId);
        validate(dish, dishId);

        return dishPersistencePort.updateDish(dish);
    }

    public void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active) {
        Dish dish = dishPersistencePort.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Plato no encontrado"));
        Restaurant restaurant = restaurantPersistencePort.findById(restaurantId)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado"));
        if (!restaurant.getOwnerId().equals(ownerId)) {
            throw new IllegalArgumentException("No eres propietario de este restaurante.");
        }
        if (!dish.getRestaurantId().equals(restaurantId)) {
            throw new IllegalArgumentException("Este plato no pertenece a tu restaurante.");
        }
        dish.setActive(active);
        dishPersistencePort.updateDish(dish);
    }



    public void validate(Dish dish, Long dishId) {

        Dish dishFounded = dishPersistencePort.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException("Plato no encontrado"));


        dish.setName(dishFounded.getName());

        Restaurant restaurant = restaurantPersistencePort.findById(dish.getRestaurantId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurante no encontrado"));

        if (!restaurant.getOwnerId().equals(dish.getOwnerId())) {
            throw new IllegalArgumentException("No eres el propietario de este restaurante.");
        }
        DishUpdateValidator.validateDish(dish);

    }



}
