package com.example.ecomerce.service;

import com.example.ecomerce.domain.Cart;
import com.example.ecomerce.domain.EcomOrder;
import com.example.ecomerce.domain.Product;
import com.example.ecomerce.domain.User;
import com.example.ecomerce.dto.ProductDTO;
import com.example.ecomerce.exception.RecordNotFoundException;
import com.example.ecomerce.repository.CartRepository;
import com.example.ecomerce.repository.OrderRepository;
import com.example.ecomerce.repository.UserRepository;
import com.example.ecomerce.util.GetAuthenticationUserId;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    ProductService productService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    OrderRepository orderRepository;
    public EcomOrder placeOrder() {
        Long id = GetAuthenticationUserId.getId();
        List<ProductDTO> productDTOList = productService.showCart();
        if (productDTOList.stream().allMatch(productDTO -> productDTO.getUserId().equals(id))) {
            User user = userRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("user not found"));
            double totalPrice = productDTOList.stream()
                    .mapToDouble(ProductDTO::getPrice)
                    .sum();

            EcomOrder order = EcomOrder.builder()
                    .user(user)
                    .status("placed")
                    .totalPrice(totalPrice)
                    .createdAt(LocalDateTime.now())
                    .build();

            return orderRepository.save(order);
        } else {
            throw new RecordNotFoundException("something went Wrong");
        }
    }
}
