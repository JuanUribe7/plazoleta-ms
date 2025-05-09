package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.pagination;

import com.example.plazoleta.ms_plazoleta.domain.model.CategoryType;
import com.example.plazoleta.ms_plazoleta.domain.model.Dish;
import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.DishQueryPort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.DishEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.DishRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DishQueryAdapter implements DishQueryPort {

    private final DishRepository dishRepository;

    public DishQueryAdapter(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public PaginatedResult<Dish> findActiveByRestaurantAndCategory(Long restaurantId, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<DishEntity> result = (category == null || category.isBlank())
                ? dishRepository.findByRestaurantIdAndActiveTrue(restaurantId, pageable)
                : dishRepository.findByRestaurantIdAndCategoryAndActiveTrue(restaurantId, CategoryType.valueOf(category), pageable);

        List<Dish> content = result.getContent().stream()
                .map(d -> new Dish(
                        d.getId(),
                        d.getName(),
                        d.getPrice().intValue(),
                        d.getDescription(),
                        d.getUrlImage(),
                        d.isActive(),
                        d.getRestaurant().getId(),
                        d.getCategory()
                ))
                .toList();

        return new PaginatedResult<>(
                content,
                result.getNumber(),
                result.getSize(),
                result.getTotalElements(),
                result.getTotalPages()
        );
    }
}

