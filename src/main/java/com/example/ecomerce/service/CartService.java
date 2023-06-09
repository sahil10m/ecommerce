package com.example.ecomerce.service;

import com.example.ecomerce.domain.Cart;
import com.example.ecomerce.domain.Product;
import com.example.ecomerce.domain.User;
import com.example.ecomerce.dto.CartDTO;
import com.example.ecomerce.exception.RecordNotFoundException;
import com.example.ecomerce.repository.CartRepository;
import com.example.ecomerce.repository.ProductRepository;
import com.example.ecomerce.repository.UserRepository;
import com.example.ecomerce.util.GetAuthenticationUserId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    private final UserRepository userRepository;


    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository ) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public Cart addProductsToCart(Long productId){

        Long id = GetAuthenticationUserId.getId();
        User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(String.format("user not found")));
        Product productList = productRepository.findById(productId).orElseThrow(() -> new RecordNotFoundException(String.format("product not found on id ==> %d", productId)));
        List<Product> productList1 = new ArrayList<>();
        productList1.add(productList);

        Cart cart = Cart.builder()
                .createdAt(LocalDateTime.now())
                .productList((productList1))
                .createdBy(user)
                .build();
        cartRepository.save(cart);
        return cart;
    }

    public Cart toCartDo(CartDTO cartDTO){
        return Cart.builder()
                .createdAt(cartDTO.getCreatedAt())
                .createdBy(cartDTO.getCreatedBy())
                .build();
    }


}
