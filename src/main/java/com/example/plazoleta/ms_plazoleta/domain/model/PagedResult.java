package com.example.plazoleta.ms_plazoleta.domain.model;

import java.util.List;

public class PagedResult<T> {

    private final List<T> content;
    private final int totalPages;
    private final long totalElements;
    private final int pageSize;
    private final int currentPage;
    private final boolean isFirst;
    private final boolean isLast;
    private final boolean hasNext;
    private final boolean hasPrevious;

    public PagedResult(List<T> content,
                       int totalPages,
                       long totalElements,
                       int pageSize,
                       int currentPage,
                       boolean isFirst,
                       boolean isLast,
                       boolean hasNext,
                       boolean hasPrevious) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.isFirst = isFirst;
        this.isLast = isLast;
        this.hasNext = hasNext;
        this.hasPrevious = hasPrevious;
    }

    public List<T> getContent() { return content; }
    public int getTotalPages() { return totalPages; }
    public long getTotalElements() { return totalElements; }
    public int getPageSize() { return pageSize; }
    public int getCurrentPage() { return currentPage; }
    public boolean isFirst() { return isFirst; }
    public boolean isLast() { return isLast; }
    public boolean hasNext() { return hasNext; }
    public boolean hasPrevious() { return hasPrevious; }
}
