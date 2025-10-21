package com.takehome.stayease.exchange;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;



///A simple DTO to handle the user's credentials during login.
@Data
public class LoginRequestDto {

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    private String password;
}
