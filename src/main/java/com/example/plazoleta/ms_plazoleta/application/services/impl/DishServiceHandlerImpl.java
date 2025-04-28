package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.request.UpdateDishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.UpdateDishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.DishDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.DishServiceHandler;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.ICreateDishServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IUpdateDishServicePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DishServiceHandlerImpl implements DishServiceHandler {

    private final ICreateDishServicePort dishServicePort;
    private final DishDtoMapper mapper;
    private final IUpdateDishServicePort updateDishServicePort;

    public DishServiceHandlerImpl(ICreateDishServicePort dishServicePort,
                                  @Qualifier("dishDtoMapperImpl") DishDtoMapper mapper,
                                  IUpdateDishServicePort updateDishServicePort) {
        this.dishServicePort = dishServicePort;
        this.mapper = mapper;
        this.updateDishServicePort = updateDishServicePort;
    }


    @Override
    public DishResponseDto createDish( DishRequestDto dto) {
        Dish dish = mapper.toModel(dto);

        Dish savedDish = dishServicePort.createDish(dish);
        return mapper.toResponseDto(savedDish);
    }

    @Override
    public UpdateDishResponseDto updateDish(UpdateDishRequestDto dto, Long dishId) {
        Dish dish = mapper.updToModel(dto);
        Dish savedDish = updateDishServicePort.updateDish(dish, dishId);
        return mapper.updToResponseDto(savedDish);
    }

    public void changeDishStatus(Long dishId, Long restaurantId, Long ownerId, boolean active) {
        updateDishServicePort.changeDishStatus(dishId, restaurantId, ownerId, active);
    }



}
