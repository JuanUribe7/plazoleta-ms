package com.example.plazoleta.ms_plazoleta.infrastructure.mappers;


import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RestaurantEntityMapper {

    private RestaurantEntityMapper() {}

    public static Restaurant toModel(RestaurantEntity entity) {
        return new Restaurant(
                entity.getId(),
                entity.getName(),
                entity.getNit(),
                entity.getAddress(),
                entity.getPhoneNumber(),
                entity.getUrlLogo(),
                entity.getOwnerId(),
                entity.getEmployeesId() != null ? entity.getEmployeesId() : new ArrayList<>()
        );
    }

    public static RestaurantEntity toEntity(Restaurant model) {
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId(model.getId());
        entity.setName(model.getName());
        entity.setNit(model.getNit());
        entity.setAddress(model.getAddress());
        entity.setPhoneNumber(model.getPhone());
        entity.setUrlLogo(model.getUrlLogo());
        entity.setOwnerId(model.getOwnerId());
        entity.setEmployeesId(model.getEmployeeIds() != null ? model.getEmployeeIds() : Collections.emptyList());
        return entity;
    }

    public static List<Restaurant> toModelList(List<RestaurantEntity> entities) {
        List<Restaurant> result = new ArrayList<>();
        for (RestaurantEntity entity : entities) {
            result.add(toModel(entity));
        }
        return result;
    }
}
