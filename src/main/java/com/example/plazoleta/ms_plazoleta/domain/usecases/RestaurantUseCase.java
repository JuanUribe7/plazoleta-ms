package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.UserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant.RestaurantValidator;

import java.util.Optional;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final UserValidationPort userValidationPort;


    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort,
                             UserValidationPort userValidationPort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userValidationPort = userValidationPort;

    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {

        RestaurantValidator.validate(restaurant);
        String rol = userValidationPort.getRoleByUser(restaurant.getOwnerId());
        if (!rol.equalsIgnoreCase("OWNER")) {
            throw new IllegalArgumentException("El id no corresponde a un usuario propietario");
        }
        if (restaurantPersistencePort.findByNit(restaurant.getNit()).isPresent()) {
            throw new IllegalArgumentException("El NIT del restaurante ya está registrado.");
        }
        if (restaurantPersistencePort.findByName(restaurant.getName()).isPresent()) {
            throw new IllegalArgumentException("El nombre del restaurante ya está registrado.");
        }
        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantPersistencePort.findById(id);
    }

    }

