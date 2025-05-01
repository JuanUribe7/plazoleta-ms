package com.example.plazoleta.ms_plazoleta.domain.ports.in.Restaurant;

import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import com.example.plazoleta.ms_plazoleta.domain.model.Restaurant;

public interface ListRestaurantsServicePort {
    PagedResult<Restaurant> listAll(Pagination pagination);
}
