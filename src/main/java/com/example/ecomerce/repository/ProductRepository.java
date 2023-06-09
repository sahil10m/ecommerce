package com.example.ecomerce.repository;

import com.example.ecomerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

public List<Product> getByCategory(Long id);

@Query(value = "select * from product where category = ?1", nativeQuery = true)
public List<Product> getProductsByCategory(Long id);
}
