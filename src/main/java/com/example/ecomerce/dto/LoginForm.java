package com.example.ecomerce.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginForm {

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String username;

    @Min(value = 5)
    private String password;;
}
