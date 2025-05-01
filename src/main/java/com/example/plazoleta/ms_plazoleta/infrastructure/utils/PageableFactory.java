package com.example.plazoleta.ms_plazoleta.infrastructure.utils;

import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageableFactory {
    private PageableFactory() {}

    public static Pageable from(Pagination pagination) {
        return PageRequest.of(pagination.getPage(), pagination.getSize(), Sort.by("name").ascending());
    }
}