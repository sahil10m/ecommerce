package com.example.ecomerce.repository;

import com.example.ecomerce.domain.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.HashSet;

public interface RolesRepository extends JpaRepository<Roles, Long> {

    HashSet<Roles> getRolesByName(String role);

    @Query("SELECT r FROM Roles r WHERE r.name = :name")
    Roles getRoleByName(@Param("name") String name);
}

