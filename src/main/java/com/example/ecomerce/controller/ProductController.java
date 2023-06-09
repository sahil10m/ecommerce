package com.example.ecomerce.controller;
import com.example.ecomerce.dto.ProductDTO;
import com.example.ecomerce.dto.ProductReviewDTO;
import com.example.ecomerce.service.CartService;
import com.example.ecomerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api")
public class ProductController {


        private final ProductService productService;

        private final CartService cartService;

    public ProductController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }


    @GetMapping("/product")
    public List<ProductDTO> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/productsByCategory/{id}")
    public ResponseEntity<List<ProductDTO>> getProductByCategory(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductsByCategory(id));
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDto){
        return ResponseEntity.ok(productService.createProduct(productDto));
    }

    @PostMapping("/product/{id}/review")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<ProductDTO> addReview( @PathVariable Long id,@Valid @RequestBody ProductReviewDTO productReviewDto){
        return ResponseEntity.ok(productService.createReview(id, productReviewDto));
    }

    @PostMapping("/productDelete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id){
        return ResponseEntity.ok(productService.deleteProduct(id));
    }

    @GetMapping("/mycart")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<?> showMyCart(){
        List<ProductDTO> productDTOList = productService.showCart();
        if(productDTOList.isEmpty()){
            return ResponseEntity.ok("your cart is empty");
        }
        return ResponseEntity.ok(productService.showCart());
    }

}
