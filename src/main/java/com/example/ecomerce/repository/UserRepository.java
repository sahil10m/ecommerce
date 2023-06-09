package com.example.ecomerce.repository;

import com.example.ecomerce.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

//    User findByName(String username);
    Optional<User> findByUsername(String username);

//    Optional<User> findByEmail(String name);

//    Long findIdByCredentials(String username);
}
