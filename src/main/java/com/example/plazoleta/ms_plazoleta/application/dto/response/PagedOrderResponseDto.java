package com.example.plazoleta.ms_plazoleta.application.dto.response;

import java.util.List;

public class PagedOrderResponseDto {
    private final List<OrderResponseDto> content;
    private final int totalPages;
    private final long totalElements;
    private final int size;
    private final int page;
    private final boolean isFirst;
    private final boolean isLast;
    private final boolean hasNext;
    private final boolean hasPrevious;

    public PagedOrderResponseDto(List<OrderResponseDto> content,
                                 int totalPages, long totalElements,
                                 int size, int page, boolean isFirst,
                                 boolean isLast, boolean hasNext, boolean hasPrevious) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.size = size;
        this.page = page;
        this.isFirst = isFirst;
        this.isLast = isLast;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public List<OrderResponseDto> getContent() { return content; }
    public int getTotalPages() { return totalPages; }
    public long getTotalElements() { return totalElements; }
    public int getSize() { return size; }
    public int getPage() { return page; }
    public boolean isFirst() { return isFirst; }
    public boolean isLast() { return isLast; }
    public boolean isHasNext() { return hasNext; }
    public boolean isHasPrevious() { return hasPrevious; }
}