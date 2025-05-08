package com.example.plazoleta.ms_plazoleta.application.dto.response;

import java.util.List;

public record PageResponseDto<T>(
    List<T> content,
    int page,
    int size,
    long totalElements,
    int totalPages,
    boolean isFirst,
    boolean isLast,
    boolean hasNext,
    boolean hasPrevious
) {}
