package com.takehome.stayease.repositpryService;

import lombok.RequiredArgsConstructor;
import com.takehome.stayease.model.Role;
import com.takehome.stayease.model.User;
import com.takehome.stayease.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserRepositoryServiceImpl implements UserRepositoryService {

    private final UserRepository userRepository;

    @Override
    public User saveUser(String firstName, String lastName, String email, String password, Role role) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(password)
                .role(role)
                .build();
        return userRepository.save(user);
    }
    
    @Override
    public void checkIfUserExists(String email) {
        userRepository.findByEmail(email).ifPresent(user -> {
            throw new IllegalArgumentException("User with email " + email + " already exists.");
        });
    }
}