package com.example.plazoleta.ms_plazoleta.domain.services;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.dish.DishValidationFields;

public class DishValidationService implements DishValidationFields

{
    private final ValidationFieldsService validationFieldsService;

    public DishValidationService(ValidationFieldsService validationFieldsService) {
        this.validationFieldsService = validationFieldsService;
    }
    @Override
    public void validateDish(Dish dish) {
        validationFieldsService.validateName(dish.getName());
        validationFieldsService.validateDescription(dish.getDescription());
        validationFieldsService.validateLogo(dish.getUrlImage());

    }

}
