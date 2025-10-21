package com.takehome.stayease.repositpryService;

import com.takehome.stayease.model.Role;
import com.takehome.stayease.model.User;

public interface UserRepositoryService {
    User saveUser(String firstName, String lastName, String email, String password, Role role);
    void checkIfUserExists(String email);
}
