package com.example.ecomerce.controller;

import com.example.ecomerce.domain.ProductCategory;
import com.example.ecomerce.domain.ProductReview;
import com.example.ecomerce.dto.ProductCategoryDTO;
import com.example.ecomerce.service.ProductCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    public ProductCategoryController(ProductCategoryService productCategoryService) {
        this.productCategoryService = productCategoryService;
    }

    @PostMapping("/productCategory")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductCategoryDTO> createCategory(@Valid @RequestBody ProductCategoryDTO productCategoryDTO){
        productCategoryDTO.setStatus(true);
        productCategoryDTO.setCreatedAt(LocalDateTime.now());
        return ResponseEntity.ok(productCategoryService.saveProductCategory(productCategoryDTO));
    }


    @GetMapping("/productCategory")
    public ResponseEntity<List<ProductCategoryDTO>> getAllProductCategory(){
        return ResponseEntity.ok(productCategoryService.getAllProductCategories());
    }


    @GetMapping("/productCategory/{id}")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<ProductCategoryDTO> getProductCategoryByIdCustomer(@PathVariable Long id){
        return ResponseEntity.ok(productCategoryService.getProductCategoryByIdCustomer(id));

    }

    @PatchMapping("/productCategory/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> deleteProductCategoryById(@PathVariable Long id){
        return  ResponseEntity.ok(productCategoryService.deleteProductCategoryById(id));
    }

}
