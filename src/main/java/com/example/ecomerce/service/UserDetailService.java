package com.example.ecomerce.service;

import com.example.ecomerce.domain.User;
import com.example.ecomerce.dto.CustomUserDetails;
import com.example.ecomerce.exception.RecordNotFoundException;
import com.example.ecomerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;


@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    AdminRepository adminRepository;


// sahil@admindb
    @Override
    public UserDetails loadUserByUsername(String username) {

           User user = userRepository.findByUsername(username).orElseThrow(() -> new RecordNotFoundException(String.format("record not found")));

            return new CustomUserDetails(user);
    }

}
