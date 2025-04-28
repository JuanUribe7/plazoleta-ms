package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.persistence;

import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.IDishPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.DishEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public class DishJpaAdapter implements IDishPersistencePort {

    private final DishRepository dishRepository;
    private final DishEntityMapper mapper;

    public DishJpaAdapter(DishRepository dishRepository, @Qualifier("dishEntityMapperImpl") DishEntityMapper mapper) {
        this.dishRepository = dishRepository;
        this.mapper = mapper;
    }

    @Override
    public Dish saveDish(Dish dish) {
        return mapper.toModel(dishRepository.save(mapper.toEntity(dish)));

    }

    @Override
    public Dish updateDish(Dish dish) {
        return mapper.toModel(dishRepository.save(mapper.toEntity(dish)));
    }


    @Override
    public Optional<Dish> findByName(String name) {
        return dishRepository
                .findByName(name)
                .map(mapper::toModel);
    }

    @Override
    public Optional<Dish> findById(Long id) {
        return dishRepository.findById(id).map(mapper::toModel);
    }


}