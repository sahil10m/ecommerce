package com.example.ecomerce.dto;

import com.example.ecomerce.domain.Product;
import com.example.ecomerce.domain.User;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CartDTO {

    private Long id;

    @NotNull(message = "products id should not be null")
    private List<Long> productList;

    private User createdBy;

    private LocalDateTime createdAt;

    private String status;
}
