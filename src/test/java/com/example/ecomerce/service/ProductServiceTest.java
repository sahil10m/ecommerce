package com.example.ecomerce.service;

import com.example.ecomerce.domain.*;
import com.example.ecomerce.dto.ProductDTO;
import com.example.ecomerce.dto.ProductReviewDTO;
import com.example.ecomerce.repository.*;
import com.example.ecomerce.util.GetAuthenticationUserId;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

class ProductServiceTest {

    @Mock
    ProductRepository productRepository;

    @Mock
    ProductCategoryRepository productCategoryRepository;

    @Mock
    ProductReviewRepository productReviewRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    ProductService productService;

    @Before
    public void setup(){
    }

    @Test
    void shouldGetAllProducts(){
        HashSet<Roles> role = new HashSet<>();
        Roles roles = Roles.builder()
                .id(1L)
                .name("ROLES_ADMIN")
                .build();
        role.add(roles);
        User user = User.builder()
                .id(1L)
                .username("shezy@gmail.com")
                .user("shezy")
                .roles(role)
                .build();
//        CURRENT_USER = user;
        ProductCategory productCategory = ProductCategory.builder()
                .id(1L)
                .user(user)
                .categoryName("shampoo")
                .createdAt(LocalDateTime.now())
                .status(true)
                .build();

        ProductReview productReview = ProductReview.builder()
                .product(new Product())
                .createdAt(LocalDateTime.now())
                .build();

        ProductReviewDTO productReviewDTO = ProductReviewDTO.builder()
                .product(productReview.getProduct().getId())
                .createdAt(productReview.getCreatedAt())
                .build();

        List<ProductReviewDTO> productReviewDTOS = new ArrayList<>();
        productReviewDTOS.add(productReviewDTO);

        Product product = Product.builder()
                .id(1L)
                .productName("pantene")
                .price(2.00)
                .status(true)
                .user(user)
                .category(productCategory)
                .review(Arrays.asList(productReview))
                .build();

        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productRepository.findAll()).thenReturn(products);
        List<ProductDTO> result = productService.getAllProducts();
        assertNotNull(result);
        ProductDTO productDTO = ProductDTO.builder()
                .productName(product.getProductName())
                .reviews(productReviewDTOS)
                .category(productCategory.getId())
                .price(product.getPrice())
                .build();

//        assertEquals(result.get(0), productsDTO);

    }

}