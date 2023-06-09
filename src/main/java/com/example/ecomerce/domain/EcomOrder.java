package com.example.ecomerce.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "ecom_order")
public class EcomOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @JoinColumn(name = "createdBy" , referencedColumnName = "id")
    @ManyToOne
    private User user;

    @Column(name="total_price")
    private Double totalPrice;

    private String status;

    @OneToOne
    @JoinColumn(name = "cartId", referencedColumnName = "id")
    private Cart cart;

}
