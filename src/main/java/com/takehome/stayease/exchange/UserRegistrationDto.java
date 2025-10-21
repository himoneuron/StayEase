package com.takehome.stayease.exchange;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;


/*
 * This DTO is used as the input when a new user registers. 
 * It carries the data from the client to the server and includes validation 
 * rules to ensure the data is correct before we even process it.
 */
@Data
public class UserRegistrationDto {

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Please provide a valid email address")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
             message = "Password must contain at least one uppercase letter, one lowercase letter, one number, and one special character.")
    private String password;
    
    // The role will be handled in the service layer, so we don't need it here.
    // This keeps the registration API simpler for the end-user.
}