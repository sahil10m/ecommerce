package com.example.ecomerce.repository;

import com.example.ecomerce.domain.EcomOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<EcomOrder, Long> {

}
