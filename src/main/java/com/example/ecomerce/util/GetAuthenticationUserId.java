package com.example.ecomerce.util;

import com.example.ecomerce.dto.CustomUserDetails;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetAuthenticationUserId {
    public static Long getId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) principal;
            // Assuming your user details class has a getUserId() method
            return userDetails.getUser().getId();
        }

        return null; // Return null or handle the case where the principal object is not an instance of UserDetails
    }}
