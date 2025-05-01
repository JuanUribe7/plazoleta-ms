package com.example.plazoleta.ms_plazoleta.domain.usecases.list;

import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;
import com.example.plazoleta.ms_plazoleta.domain.ports.in.ListRestaurantsServicePort;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.RestaurantPersistencePort;

public class ListRestaurantsUseCase implements ListRestaurantsServicePort {
    private final RestaurantPersistencePort port;

    public ListRestaurantsUseCase(RestaurantPersistencePort port) {
        this.port = port;
    }

    @Override
    public PagedResult<Restaurant> listAll(Pagination pagination) {
        return port.findAllOrderedByName(pagination);
    }
}
