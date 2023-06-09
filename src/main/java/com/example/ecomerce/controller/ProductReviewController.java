//package com.example.ecomerce.controller;
//
//import com.example.ecomerce.domain.Product;
//import com.example.ecomerce.domain.ProductReview;
//import com.example.ecomerce.service.ProductReviewService;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api")
//public class ProductReviewController {
//
//    private final ProductReviewService productReviewService;
//
//    public ProductReviewController(ProductReviewService productReviewService) {
//        this.productReviewService = productReviewService;
//    }
//
////    @GetMapping("/productReview/{id}")
////    public ResponseEntity<List<ProductReview>> getReviewsByProductId(@PathVariable Long id){
////        return ResponseEntity.ok(productReviewService.getReviewsByProductId(id));
////    }
//
////    @PostMapping("/productReview/{id}")
////    public ResponseEntity<Product> createReview(@PathVariable Long id, ProductReview productReview){
////        return ResponseEntity.ok(productReviewService.creaateReview(id, productReview));
////    }
//}
