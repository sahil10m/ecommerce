package com.example.ecomerce.dto;


import lombok.Builder;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductReviewDTO {

    private Long id;

    @NotBlank()
    private String review;

    private Long userId;

    private Long product;

    private LocalDateTime createdAt;

}
