package com.takehome.stayease;


import com.takehome.stayease.model.Role;
import com.takehome.stayease.model.User;
import com.takehome.stayease.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StayeaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(StayeaseApplication.class, args);
	}

	@Bean
    CommandLineRunner run(UserRepository userRepository, PasswordEncoder passwordEncoder,
                          @Value("${admin.email}") String adminEmail,
                          @Value("${admin.password}") String adminPassword) {
        return args -> {
            // Check if an admin user already exists
            if (!userRepository.existsByRole(Role.ADMIN)) {
                System.out.println("No ADMIN user found. Creating one...");
                User adminUser = User.builder()
                        .firstName("Admin")
                        .lastName("User")
                        .email(adminEmail)
                        .password(passwordEncoder.encode(adminPassword))
                        .role(Role.ADMIN)
                        .build();
                userRepository.save(adminUser);
                System.out.println("Admin user created successfully with email: " + adminEmail);
            } else {
                System.out.println("Admin user already exists. Skipping creation.");
            }
        };
    }


}
