package com.example.plazoleta.ms_plazoleta.domain.usecases.restaurant;

import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.restaurant.ListRestaurantsServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Pagination.RestaurantPaginationPort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Persistence.RestaurantPersistencePort;
import com.example.plazoleta.ms_plazoleta.infrastructure.entities.RestaurantEntity;
import com.example.plazoleta.ms_plazoleta.infrastructure.helpers.PageableQuery;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.RestaurantEntityMapper;

public class ListRestaurantsUseCase implements ListRestaurantsServicePort {
    private final RestaurantPersistencePort port;
    private final RestaurantPaginationPort paginationPort;

    public ListRestaurantsUseCase(RestaurantPersistencePort port, RestaurantPaginationPort paginationPort) {
        this.port = port;
        this.paginationPort = paginationPort;
    }

    @Override
    public PagedResult<Restaurant> listAll(Pagination pagination) {
        PageableQuery<RestaurantEntity> query = new PageableQuery<>(
                pagination,
                () -> port.findAllOrderedByName(pagination) // devuelve Page<RestaurantEntity>
        );

        return paginationPort.paginate(query, RestaurantEntityMapper::toModel);
    }
}
