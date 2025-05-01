package com.example.plazoleta.ms_plazoleta.domain.model;

public class Pagination {
    private final int page;
    private final int size;

    public Pagination(int page, int size) {
        if (page < 0) throw new IllegalArgumentException("Page must be non-negative.");
        if (size <= 0 || size > 100) throw new IllegalArgumentException("Size must be between 1 and 100.");
        this.page = page;
        this.size = size;
    }

    public int getPage() { return page; }
    public int getSize() { return size; }
}
