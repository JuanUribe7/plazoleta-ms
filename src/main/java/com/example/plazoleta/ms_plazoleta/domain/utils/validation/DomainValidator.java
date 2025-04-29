package com.example.plazoleta.ms_plazoleta.domain.utils.validation;

import com.example.plazoleta.ms_plazoleta.commons.constants.ExceptionMessages;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IRestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IUserValidationPort;
import com.example.plazoleta.ms_plazoleta.infrastructure.exceptions.OwnerNotFoundException;

public class DomainValidator {
    private final IRestaurantPersistencePort rPort;
    private final IDishPersistencePort       dPort;
    private final IUserValidationPort        uPort;

    public DomainValidator(IRestaurantPersistencePort r, IDishPersistencePort d, IUserValidationPort u) {
        this.rPort = r; this.dPort = d; this.uPort = u;
    }

    // A) Usuario existe y es OWNER
    public void validateUserIsOwner(Long ownerId) {
        try {
            if (!"OWNER".equalsIgnoreCase(uPort.getRoleByUser(ownerId)))
                throw new IllegalArgumentException(ExceptionMessages.USER_NOT_OWNER);
        } catch (OwnerNotFoundException e) {
            throw new IllegalArgumentException(ExceptionMessages.USER_NOT_EXIST);
        }
    }

    // B) Restaurante existe y es propiedad de ownerId
    public Restaurant validateRestaurantOwnership(Long restId, Long ownerId) {
        Restaurant r = rPort.findById(restId)
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessages.RESTAURANT_NOT_EXIST));
        if (!r.getOwnerId().equals(ownerId))
            throw new IllegalArgumentException(ExceptionMessages.CREATE_NOT_OWNER);
        return r;
    }

    // C) Plato existe, pertenece a restId y ownerId
    public Dish validateDishOwnership(Long dishId, Long restId, Long ownerId) {
        Dish d = dPort.findById(dishId)
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessages.DISH_NOT_FOUND));
        if (!d.getRestaurantId().equals(restId))
            throw new IllegalArgumentException(ExceptionMessages.DISH_NOT_BELONG);
        Restaurant r = rPort.findById(restId)
                .orElseThrow(() -> new IllegalArgumentException(ExceptionMessages.RESTAURANT_NOT_EXIST));
        if (!r.getOwnerId().equals(ownerId))
            throw new IllegalArgumentException(ExceptionMessages.NOT_OWNER);
        return d;
    }

    // D) Chequear nombre de plato duplicado
    public void validateNewDishName(String name) {
        if (dPort.findByName(name).isPresent())
            throw new IllegalArgumentException(ExceptionMessages.DISH_NAME_DUPLICATE);
    }

    // E) Chequear NIT+nombre de restaurante duplicados
    public void validateNewRestaurant(String nit, String name) {
        if (rPort.findByNit(nit).isPresent())
            throw new IllegalArgumentException(ExceptionMessages.NIT_DUPLICATE);
        if (rPort.findByName(name).isPresent())
            throw new IllegalArgumentException(ExceptionMessages.RESTAURANT_NAME_DUP);
    }
}