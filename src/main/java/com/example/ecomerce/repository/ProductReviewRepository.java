package com.example.ecomerce.repository;

import com.example.ecomerce.domain.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {
}
