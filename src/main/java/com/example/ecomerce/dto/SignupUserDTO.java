package com.example.ecomerce.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupUserDTO {


    @NotBlank(message = "Email is required")
//@Email(message = "Invalid email format")
private String username;
    @NotBlank(message = "username cannot be blank")
private String user;


private String password;

}
