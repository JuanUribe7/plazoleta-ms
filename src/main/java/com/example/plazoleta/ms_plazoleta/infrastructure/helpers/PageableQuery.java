package com.example.plazoleta.ms_plazoleta.infrastructure.helpers;

import com.example.plazoleta.ms_plazoleta.domain.model.Pagination;
import org.springframework.data.domain.Page;

import java.util.function.Supplier;

public class PageableQuery<E> {
    private final Pagination pagination;
    private final Supplier<Page<E>> databaseCall;

    public PageableQuery(Pagination pagination, Supplier<Page<E>> databaseCall) {
        this.pagination = pagination;
        this.databaseCall = databaseCall;
    }

    public Supplier<Page<E>> getDatabaseCall() {
        return databaseCall;
    }

    public Pagination getPagination() {
        return pagination;
    }
}