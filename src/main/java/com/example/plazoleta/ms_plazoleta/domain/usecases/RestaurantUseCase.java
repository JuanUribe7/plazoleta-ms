package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IUserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant.RestaurantValidator;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.OwnerNotFoundException;

import java.util.Optional;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final IUserValidationPort userValidationPort;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort,
                             IUserValidationPort userValidationPort) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.userValidationPort = userValidationPort;
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        RestaurantValidator.validate(restaurant);

        String rol;
        try {
            rol = userValidationPort.getRoleByUser(restaurant.getOwnerId());
        } catch (OwnerNotFoundException e) {
            throw new IllegalArgumentException("No existe un usuario con ese id.");
        }

        if (!rol.equalsIgnoreCase("OWNER")) {
            throw new IllegalArgumentException("El id no corresponde a un usuario propietario.");
        }

        if (restaurantPersistencePort.findByNit(restaurant.getNit()).isPresent()) {
            throw new IllegalArgumentException("El NIT del restaurante ya está registrado.");
        }

        if (restaurantPersistencePort.findByName(restaurant.getName()).isPresent()) {
            throw new IllegalArgumentException("El nombre del restaurante ya está registrado.");
        }

        Restaurant savedRestaurant = restaurantPersistencePort.saveRestaurant(restaurant);
        userValidationPort.updateOwnerRestaurantId(restaurant.getOwnerId(), savedRestaurant.getId());

        return savedRestaurant;
    }

    @Override
    public boolean isOwnerOfRestaurant(Long restaurantId, Long ownerId) {
        Optional<Restaurant> restaurant = restaurantPersistencePort.findById(restaurantId);
        return restaurant.isPresent() && restaurant.get().getOwnerId().equals(ownerId);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantPersistencePort.findById(id);
    }
}
