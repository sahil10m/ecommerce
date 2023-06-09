package com.example.ecomerce.controller;

import com.example.ecomerce.domain.EcomOrder;
import com.example.ecomerce.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/placeorder")
    @PreAuthorize("hasRole('ROLE_CUSTOMER')")
    public ResponseEntity<EcomOrder> placeOrder(){
        return ResponseEntity.ok(orderService.placeOrder());
    }

}
