package com.example.ecomerce.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductCategoryDTO {

    @NotBlank(message = "category should not be empty")
    private String category;

    private Long userId;

    private Boolean status;

    private LocalDateTime createdAt;

}
