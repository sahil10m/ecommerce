package com.example.ecomerce.service;

import com.example.ecomerce.domain.Roles;
import com.example.ecomerce.domain.User;
import com.example.ecomerce.dto.SignupUserDTO;
import com.example.ecomerce.repository.RolesRepository;
import com.example.ecomerce.repository.UserRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.management.relation.Role;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

//test driven approach
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RolesRepository rolesRepository;

    @Mock
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    UserService userService;


    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldAddUser(){
        HashSet<Roles> roles = new HashSet<>();

        Roles role = Roles.builder()
                .name("ROLES_CUSTOMER")
                .build();

        roles.add(role);
        SignupUserDTO signupUserDTO = new SignupUserDTO("sahil@gmail.com", "sahil", "1234");

        User user = User.builder()
                .username(signupUserDTO.getUsername())
                .user(signupUserDTO.getUser())
                .roles(roles)
                .password("1234")
                .status(true)
                .build();

        when(userRepository.save(any())).thenReturn(user);
        when(bCryptPasswordEncoder.encode(signupUserDTO.getPassword())).thenReturn("1234");
        when(rolesRepository.getRoleByName("ROLE_CUSTOMER")).thenReturn(role);


        SignupUserDTO returnSignupUserDto = SignupUserDTO.builder()
                .user("sahil")
                .username("sahil@gmail.com")
                .build();

        assertEquals(returnSignupUserDto, userService.addUser(signupUserDTO));
    }
}