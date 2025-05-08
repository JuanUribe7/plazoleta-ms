package com.example.plazoleta.ms_plazoleta.application.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TraceRequestDto {
    private Long orderId;
    private Long clientId;
    private Long restaurantId;
    private String status;
    private LocalDateTime createdAt;
    private Long assignedEmployeeId;
}
