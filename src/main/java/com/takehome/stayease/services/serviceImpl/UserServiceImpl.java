package com.takehome.stayease.services.serviceImpl;

import lombok.RequiredArgsConstructor;
import com.takehome.stayease.dto.RegisterUserRequest;
import com.takehome.stayease.exceptions.ResourceNotFoundException;
import com.takehome.stayease.exchange.AuthResponseDto;
import com.takehome.stayease.exchange.LoginRequestDto;
import com.takehome.stayease.model.Role;
import com.takehome.stayease.model.User;
import com.takehome.stayease.repository.UserRepository;
import com.takehome.stayease.repositpryService.UserRepositoryService;
import com.takehome.stayease.services.UserService;
import com.takehome.stayease.services.authService.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository; 
    private final UserRepositoryService userRepositoryService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    @Override
    public AuthResponseDto registerUser(RegisterUserRequest request) {
        userRepositoryService.checkIfUserExists(request.getEmail());

        String encryptedPassword = passwordEncoder.encode(request.getPassword());
        
        // FIX: Determine role from the request, default to CUSTOMER
        Role role = Role.CUSTOMER; // Default role
        if (request.getRole()!= null &&!request.getRole().isEmpty()) {
            try {
                role = Role.valueOf(request.getRole().toUpperCase());
            } catch (IllegalArgumentException e) {
                // Invalid role string, fall back to default or throw an exception
                // For this case, we'll stick with the default CUSTOMER role.
            }
        }


        User savedUser = userRepositoryService.saveUser(
                request.getFirstName(),
                request.getLastName(),
                request.getEmail(),
                encryptedPassword,
                role
        );

        String jwtToken = jwtService.generateToken(savedUser);
        return new AuthResponseDto(jwtToken);
    }
@Override
    public AuthResponseDto loginUser(LoginRequestDto request) {
        // FIX: Wrap authentication to catch bad credentials and return 404 as per tests
        try {
            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );
        } catch (BadCredentialsException e) {
            // The test expects a 404 for invalid credentials
            throw new ResourceNotFoundException("User", "credentials", "invalid");
        }
    
        User user = userRepository.findByEmail(request.getEmail())
               .orElseThrow(() -> new UsernameNotFoundException("User not found: " + request.getEmail()));
    
        String jwtToken = jwtService.generateToken(user);
        return new AuthResponseDto(jwtToken);
    }
    
   
    }
    


