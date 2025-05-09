package com.example.plazoleta.ms_plazoleta.infrastructure.mappers;

import com.example.plazoleta.ms_plazoleta.domain.model.PaginatedResult;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper
public interface PageMapper {
    PageMapper INSTANCE = Mappers.getMapper(PageMapper.class);

    default <T> PaginatedResult<T> toPaginatedResult(Page<T> page) {
        return new PaginatedResult<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages()
        );
    }

}