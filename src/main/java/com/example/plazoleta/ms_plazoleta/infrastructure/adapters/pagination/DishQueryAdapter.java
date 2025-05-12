package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.pagination;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishQueryPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.DishPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.DishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.DishEntityMapper;

import java.util.List;
@RequiredArgsConstructor
@Repository
public class DishQueryAdapter implements DishQueryPort {

    private final DishPersistencePort dishPort;


    @Override
    public PaginatedResult<Dish> findActiveByRestaurantAndCategory(Long restaurantId, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Dish> result = (category == null)
                ? dishPort.findByRestaurantIdAndActiveTrue(restaurantId, pageable)
                : dishPort.findByRestaurantIdAndCategoryAndActiveTrue(restaurantId, category, pageable);

        List<Dish> content = result.getContent();

        return new PaginatedResult<>(
                content,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}