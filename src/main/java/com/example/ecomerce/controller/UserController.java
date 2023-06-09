package com.example.ecomerce.controller;

import com.example.ecomerce.domain.User;
import com.example.ecomerce.dto.LoginForm;
import com.example.ecomerce.dto.SignupUserDTO;
import com.example.ecomerce.repository.UserRepository;
import com.example.ecomerce.service.UserService;
import com.example.ecomerce.util.AuthenticationResponse;
import com.example.ecomerce.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserRepository userRepository;
    private final UserService userService;
    @Autowired
    private final AuthenticationManager authenticationManager;

    private final UserDetailsService userDetailsService;

    private final JwtUtil jwtUtil;



    public UserController(UserService userService, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/customer/signup")
    public ResponseEntity<?> addUser(@Valid @RequestBody SignupUserDTO user){
//        if(userRepository.findByEmail(user.getEmail()).isPresent()){
//            return ResponseEntity.badRequest().body("Email already exist");
//        }
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
           return ResponseEntity.badRequest().body("username already exist");
        }
        return ResponseEntity.ok(userService.addUser(user));
    }


    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginForm loginForm) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginForm.getUsername(),loginForm.getPassword())
            );
        }
        catch(Exception e){
            throw new Exception("Incorrect Username or Password ! ",e);
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginForm.getUsername());
        String jwtToken = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwtToken));
    }



    @PostMapping("/admin/signup")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> addAdmin(@RequestBody User user){
        return ResponseEntity.ok(userService.addAdmin(user));
    }



//    @PostMapping("/admin/signup")
//    @PreAuthorize("hasRole('ADMIN')")
//    public String addAdmin(User user){
//        return "hasRole('ADMIN')";
//    }







//    @GetMapping("/user")
//    public ResponseEntity<List<User>> getAllUsers(){
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
//
//    @GetMapping("/user/{id}")
//    public ResponseEntity<User> getUserById(@PathVariable Long id){
//        return ResponseEntity.ok(userService.findById(id));
//    }
//
//    @PostMapping("/user")
//    public ResponseEntity<User> createUser(@RequestBody User user){
////        user.setStatus(true);
//        return ResponseEntity.ok(userService.createUser(user));
//    }
//
//    @PatchMapping("/user/delete/{id}")
//    public ResponseEntity<User> deleteUser(@RequestParam("delete") Boolean del,  @PathVariable Long id) throws Exception {
//        User user = userService.findById(id);
//        user.setStatus(del);
//        User deletedUser = userService.createUser(user);
//         return ResponseEntity.ok(deletedUser);
//    }





}
