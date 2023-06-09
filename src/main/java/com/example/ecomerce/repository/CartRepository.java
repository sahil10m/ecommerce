package com.example.ecomerce.repository;

import com.example.ecomerce.domain.Cart;
import com.example.ecomerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

//    @Query(value = "SELECT c FROM Cart c WHERE c.user.id = :userId", nativeQuery = true)
//    List<Cart> findByUserId(Long userId);

    List<Cart> findByCreatedBy(User user);

}
