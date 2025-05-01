package com.example.plazoleta.ms_plazoleta.infrastructure.adapters.Pagination;

import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import com.example.plazoleta.ms_plazoleta.domain.ports.out.Pagination.RestaurantPaginationPort;
import com.example.plazoleta.ms_plazoleta.infrastructure.helpers.PageableQuery;
import com.example.plazoleta.ms_plazoleta.infrastructure.mappers.PagedResultMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SpringDataPaginationAdapter implements RestaurantPaginationPort {

    @Override
    public <E, M> PagedResult<M> paginate(PageableQuery<E> query, Function<E, M> mapper) {
        Page<E> page = query.getDatabaseCall().get();
        return PagedResultMapper.map(page, mapper);
    }
}