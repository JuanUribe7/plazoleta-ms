package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.pagination;

import com.example.plazoleta.ms_plazoleta.application.dto.response.PageResponseDto;
import com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant.RestaurantBasicResponseDto;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantQueryPort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.repositories.RestaurantRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RestaurantQueryAdapter implements RestaurantQueryPort {

    private final RestaurantRepository repository;

    public RestaurantQueryAdapter(RestaurantRepository repository) {
        this.repository = repository;
    }

    @Override
    public PageResponseDto<RestaurantBasicResponseDto> findAllSortedByName(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<RestaurantEntity> entities = repository.findAll(pageable);

        List<RestaurantBasicResponseDto> content = entities.getContent().stream()
            .map(entity -> new RestaurantBasicResponseDto(entity.getName(), entity.getUrlLogo()))
            .toList();

        return new PageResponseDto<>(
                content,
                entities.getNumber(),
                entities.getSize(),
                entities.getTotalElements(),
                entities.getTotalPages(),
                entities.isFirst(),
                entities.isLast(),
                entities.hasNext(),
                entities.hasPrevious()
        );
    }
}
