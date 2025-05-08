package com.example.plazoleta.ms_plazoleta.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackingRequestDto {
    private Long orderId;
    private String status;

    private Long assignedEmployeeId;
}
