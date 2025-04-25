package com.example.plazoleta.ms_plazoleta.domain.usecases;

import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IRestaurantServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.utils.validation.Validator;
import com.example.plazoleta.ms_plazoleta.infrastructure.client.UserFeignClient;

import java.util.regex.Pattern;

public class RestaurantUseCase implements IRestaurantServicePort {
    private static final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{1,13}$");

    private final IRestaurantPersistencePort restaurantPersistencePort;
    private final UserFeignClient usuarioFeignClient;

    public RestaurantUseCase(IRestaurantPersistencePort restaurantPersistencePort, UserFeignClient userClient) {
        this.restaurantPersistencePort = restaurantPersistencePort;
        this.usuarioFeignClient = userClient;

    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant) {

        Validator.validate(restaurant, restaurantPersistencePort);
        String rol = usuarioFeignClient.obtenerRolPorUsuario(restaurant.getOwnerId());
        if (!rol.equalsIgnoreCase("OWNER")) {
            throw new IllegalArgumentException("El idPropietario no corresponde a un usuario propietario");
        }
        return restaurantPersistencePort.saveRestaurant(restaurant);
    }

    }

