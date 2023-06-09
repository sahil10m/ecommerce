package com.example.ecomerce.domain;

import lombok.*;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    private Boolean status;

//    @OneToMany(mappedBy = "category")
//    private List<Product> product;

    @ManyToOne
    @JoinColumn(name = "createdBy", referencedColumnName = "id")
    private User user;


}
