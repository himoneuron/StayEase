package com.takehome.stayease.services;


import com.takehome.stayease.dto.RegisterUserRequest;
import com.takehome.stayease.exchange.AuthResponseDto;
import com.takehome.stayease.exchange.LoginRequestDto;


public interface UserService {
    AuthResponseDto registerUser(RegisterUserRequest registerUserRequest);
    AuthResponseDto loginUser(LoginRequestDto loginRequestDto);
}
