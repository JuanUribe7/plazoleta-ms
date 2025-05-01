package com.example.plazoleta.ms_plazoleta.application.dto.response.restaurant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PagedRestaurantResponseDto {
    private List<RestaurantSimpleResponseDto> restaurants;
    private PaginationInfo pagination;



    public List<RestaurantSimpleResponseDto> getRestaurants() {
        return restaurants;
    }

    public PaginationInfo getPagination() {
        return pagination;
    }
    @AllArgsConstructor
    public static class PaginationInfo {
        private int totalPages;
        private long totalElements;
        private int pageSize;
        private int currentPage;
        private boolean isFirst;
        private boolean isLast;
        private boolean hasNext;
        private boolean hasPrevious;



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