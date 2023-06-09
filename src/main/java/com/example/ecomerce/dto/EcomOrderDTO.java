package com.example.ecomerce.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EcomOrderDTO {

    private Long id;

    private LocalDateTime createdAt;

    private Long createdBy;

    private Double totalPrice;

    private String status;


}
