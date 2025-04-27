package com.example.plazoleta.ms_plazoleta.application.services.impl;

import com.example.plazoleta.ms_plazoleta.application.dto.request.DishRequestDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.DishResponseDto;
import com.example.plazoleta.ms_plazoleta.application.mappers.DishDtoMapper;
import com.example.plazoleta.ms_plazoleta.application.services.DishService;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.IDishServicePort;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl implements DishService {

    private final IDishServicePort dishServicePort;
    private final DishDtoMapper mapper;

    public DishServiceImpl(IDishServicePort dishServicePort, @Qualifier("dishDtoMapperImpl") DishDtoMapper mapper) {
        this.dishServicePort = dishServicePort;
        this.mapper = mapper;
    }



    @Override
    public DishResponseDto createDish(Long restaurantId, DishRequestDto dto, Long ownerId) {
        Dish dish = mapper.toModel(dto);
        dish.setRestaurantId(restaurantId);
        dish.setOwnerId(ownerId);
        Dish savedDish = dishServicePort.createDish(dish);
        return mapper.toResponseDto(savedDish);
    }


}
