package com.example.ecomerce.dto;


import com.example.ecomerce.domain.ProductReview;
import lombok.*;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProductDTO {



    @NotBlank(message = "name should not be blank")
    private String productName;

    private Long userId;

    @NotNull(message = "price should not be null")
    @Min(value = 0)
    private Double price;

    @NotNull(message = "category id should not be null")
    private Long category;

    private Boolean status;

    private List<ProductReviewDTO> reviews;

}
