package com.example.ecomerce.repository;

import com.example.ecomerce.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {


}
