package com.example.plazoleta.ms_plazoleta.domain.ports.out.Pagination;

import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.infrastructure.helpers.PageableQuery;

import java.util.function.Function;

public interface RestaurantPaginationPort {
    <E, M> PagedResult<M> paginate(PageableQuery<E> query, Function<E, M> mapper);

}
