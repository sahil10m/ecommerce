package com.example.ecomerce.controller;

import com.example.ecomerce.domain.Cart;
import com.example.ecomerce.domain.Product;
import com.example.ecomerce.service.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/addToCart/{productId}/product")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<Cart> addProductsToCart(@PathVariable Long productId){
            return ResponseEntity.ok(cartService.addProductsToCart(productId));
    }


}
