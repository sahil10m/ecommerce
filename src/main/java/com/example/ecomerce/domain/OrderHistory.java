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
public class OrderHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    private String Status;

    @ManyToOne()
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private EcomOrder order;

}