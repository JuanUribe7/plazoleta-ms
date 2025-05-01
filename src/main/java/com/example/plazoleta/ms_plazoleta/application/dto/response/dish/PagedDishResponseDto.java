package com.example.plazoleta.ms_plazoleta.application.dto.response.dish;

import java.util.List;

public class PagedDishResponseDto {

    private List<DishSimpleResponseDto> dishes;
    private PaginationInfo pagination;

    public PagedDishResponseDto() {} // ← NECESARIO PARA JACKSON

    public PagedDishResponseDto(List<DishSimpleResponseDto> dishes, PaginationInfo pagination) {
        this.dishes = dishes;
        this.pagination = pagination;
    }

    public List<DishSimpleResponseDto> getDishes() {
        return dishes;
    }

    public PaginationInfo getPagination() {
        return pagination;
    }

    public static class PaginationInfo {

        private int totalPages;
        private long totalElements;
        private int pageSize;
        private int currentPage;
        private boolean isFirst;
        private boolean isLast;
        private boolean hasNext;
        private boolean hasPrevious;

        public PaginationInfo() {} // ← NECESARIO

        public PaginationInfo(int totalPages, long totalElements, int pageSize,
                              int currentPage, boolean isFirst, boolean isLast,
                              boolean hasNext, boolean hasPrevious) {
            this.totalPages = totalPages;
            this.totalElements = totalElements;
            this.pageSize = pageSize;
            this.currentPage = currentPage;
            this.isFirst = isFirst;
            this.isLast = isLast;
            this.hasNext = hasNext;
            this.hasPrevious = hasPrevious;
        }

        // Getters públicos para todos los campos
        public int getTotalPages() { return totalPages; }
        public long getTotalElements() { return totalElements; }
        public int getPageSize() { return pageSize; }
        public int getCurrentPage() { return currentPage; }
        public boolean isFirst() { return isFirst; }
        public boolean isLast() { return isLast; }
        public boolean isHasNext() { return hasNext; }
        public boolean isHasPrevious() { return hasPrevious; }
    }
}
