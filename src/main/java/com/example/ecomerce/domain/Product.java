package com.example.ecomerce.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;

    @ManyToOne
    @JoinColumn(name="createdBy", referencedColumnName = "id")
    private User user;

    private Double price;

    private Boolean status;



    @JoinColumn(name = "category", referencedColumnName = "id")
    @ManyToOne()
    private ProductCategory category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductReview> review;

//    @ManyToMany(mappedBy = "productList")
//    @JsonIgnore
//    private List<Cart> cartList;

}
