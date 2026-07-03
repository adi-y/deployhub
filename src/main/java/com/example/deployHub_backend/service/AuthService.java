package com.example.deployHub_backend.service;

import com.example.deployHub_backend.dto.request.LoginRequest;
import com.example.deployHub_backend.dto.request.RegisterRequest;
import com.example.deployHub_backend.dto.response.AuthResponse;

public interface AuthService {
    void register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
