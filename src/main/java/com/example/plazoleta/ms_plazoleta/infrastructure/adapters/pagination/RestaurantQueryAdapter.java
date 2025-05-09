package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.pagination;

import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantQueryPort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.PageMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.RestaurantEntityMapper;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class RestaurantQueryAdapter implements RestaurantQueryPort {

    private final RestaurantRepository repository;

    public RestaurantQueryAdapter(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public PaginatedResult<Restaurant> findAllSortedByName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<RestaurantEntity> entities = repository.findAll(pageable);

        return PageMapper.INSTANCE.toPaginatedResult(
                entities.map(RestaurantEntityMapper::toModel)
        );
    }
}