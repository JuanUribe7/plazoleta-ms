package com.example.plazoleta.ms_plazoleta.infrastructure.mappers;

import com.example.plazoleta.ms_plazoleta.domain.model.PagedResult;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public class PagedResultMapper {

    private PagedResultMapper() {}

    public static <E, M> PagedResult<M> map(Page<E> page, Function<E, M> mapper) {
        List<M> content = page.getContent().stream().map(mapper).toList();

        return new PagedResult<>(
                content,
                page.getTotalPages(),
                page.getTotalElements(),
                page.getSize(),
                page.getNumber(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
}
