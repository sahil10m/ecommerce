package com.example.ecomerce.service;

import com.example.ecomerce.domain.Roles;
import com.example.ecomerce.domain.User;
import com.example.ecomerce.dto.SignupUserDTO;
import com.example.ecomerce.exception.RecordNotFoundException;
import com.example.ecomerce.repository.RolesRepository;
import com.example.ecomerce.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RolesRepository rolesRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

//    public List<User> getAllUsers(){
//       List<User> users = userRepository.findAll();
//       return users;
//    }
//
//    public User createUser(User user){
//        return userRepository.save(user);
//    }
//
//    public User findById(Long id){
//        Optional<User> user = userRepository.findById(id);
//        if(user.isPresent()){
//            return user.get();
//        }
//        throw new RecordNotFoundException(String.format("no record found on id ==> %d", id));
//    }

    public SignupUserDTO addUser(SignupUserDTO user){
        User user1 = User.builder()
                .username(user.getUsername())
//                .email(user.getEmail())
                .password(bCryptPasswordEncoder.encode(user.getPassword()))
                .roles(assignRoleToUser("ROLE_CUSTOMER"))
                .status(true)
                .build();
        User savedUser = userRepository.save(user1);

//
        return SignupUserDTO.builder()
                .username(savedUser.getUsername())
                .user(savedUser.getUser())
                .build();

//        User newUser = new User();
//        try {
//            newUser.setEmail(user.getEmail());
//            newUser.setUsername(user.getUsername());
//            newUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//            newUser.setRoles(assignRoleToUser("CUSTOMER"));
//            return userRepository.save(newUser);
//        }catch(Exception e){
//            throw new RuntimeException("some thing went wrong in adding new user" , e);
//        }
    }

//    public Set<Roles> assignRoleCustomerToUser(User user){
//        Set<Roles> rolesSet = new HashSet<>();
//        rolesSet.add(rolesRepository.getRoleByName("customer"));
//        return rolesSet;
//    }


    public Set<Roles> assignRoleToUser(String role){
        try {
            Set<Roles> rolesSet = new HashSet<>();
            rolesSet.add(rolesRepository.getRoleByName(role));
            return rolesSet;
        }catch(Exception e ){
            throw new RuntimeException("something went wrong in adding roles");
        }
    }



    public User addAdmin(User user){
        try{
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            user.setRoles(assignRoleToUser("ROLE_ADMIN"));
            return userRepository.save(user);
        }catch(Exception e){
            throw new RuntimeException("some thing went wrong in adding new admin");
        }
    }
}
