package com.example.plazoleta.ms_plazoleta.domain.usecases;


import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IUserValidationPort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.DomainValidator;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.restaurant.RestaurantValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Optional;

public class RestaurantUseCase implements IRestaurantServicePort {

    private final IRestaurantPersistencePort restaurantPort;
    private final IUserValidationPort       userValidationPort;
    private final DomainValidator validator;

    public RestaurantUseCase(
            IRestaurantPersistencePort restaurantPort,
            IUserValidationPort userValidationPort
    ) {
        this.restaurantPort     = restaurantPort;
        this.userValidationPort = userValidationPort;
        this.validator          = new DomainValidator(restaurantPort, null, userValidationPort);
    }

    @Override
    public Restaurant createRestaurant(Restaurant restaurant) {
        // 1) Validar campos del DTO
        RestaurantValidator.validate(restaurant);

        // 2) Comprobar que el usuario exista y sea OWNER
        validator.validateUserIsOwner(restaurant.getOwnerId());

        // 3) NIT y nombre únicos
        validator.validateNewRestaurant(
                restaurant.getNit(),
                restaurant.getName()
        );

        // 4) Persistir
        Restaurant saved = restaurantPort.saveRestaurant(restaurant);

        // 5) Asociar restaurante al usuario
        userValidationPort.updateOwnerRestaurantId(
                restaurant.getOwnerId(),
                saved.getId()
        );

        return saved;
    }

    @Override
    public Page<Restaurant> getAllRestaurantsPaged(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        return restaurantPort.findAllPagedSortedByName(pageable);
    }

    @Override
    public boolean isOwnerOfRestaurant(Long restaurantId, Long ownerId) {
        return restaurantPort.findById(restaurantId)
                .map(r -> r.getOwnerId().equals(ownerId))
                .orElse(false);
    }

    @Override
    public Optional<Restaurant> findById(Long id) {
        return restaurantPort.findById(id);
    }
}