//package com.example.ecomerce.service;
//
//import com.example.ecomerce.domain.Product;
//import com.example.ecomerce.domain.ProductReview;
//import com.example.ecomerce.exception.RecordNotFoundException;
//import com.example.ecomerce.repository.ProductRepository;
//import com.example.ecomerce.repository.ProductReviewRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class ProductReviewService {
//
//    private final ProductReviewRepository productReviewRepository;
//
//    private final ProductRepository productRepository;
//
//    public ProductReviewService(ProductReviewRepository productReviewRepository, ProductRepository productRepository) {
//        this.productReviewRepository = productReviewRepository;
//        this.productRepository = productRepository;
//    }
//
//    public List<ProductReview> getReviewsByProductId(Long id){
//        Optional<Product> haveProduct = productRepository.findById(id);
//        if(haveProduct.isPresent()){
//            return haveProduct.get()
//        }
//        throw new RecordNotFoundException(String.format("record not found on id ==> %d" , id));
//    }
//
//    public Product createReview(Long id, ProductReview productReview){
//
//        Optional<Product> product = productRepository.findById(id);
//
//        if(product.isPresent()){
//            product.get().getReview().add(productReview);
//            return productRepository.save(product.get());
//        }
//
//        throw new RecordNotFoundException(String.format("record not found on id ==> %d", id));
//    }
//
//}
