package com.example.plazoleta.ms_plazoleta.application.dto.response;

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

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaginationInfo {
        private int totalPages;
        private long totalElements;
    }
}