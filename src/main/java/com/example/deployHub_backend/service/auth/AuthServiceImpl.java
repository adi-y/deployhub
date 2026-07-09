package com.example.deployHub_backend.service.auth;

import com.example.deployHub_backend.dto.request.LoginRequest;
import com.example.deployHub_backend.dto.request.RegisterRequest;
import com.example.deployHub_backend.dto.response.AuthResponse;
import com.example.deployHub_backend.entity.Users;
import com.example.deployHub_backend.exception.EmailAlreadyExistsException;
import com.example.deployHub_backend.exception.InvalidCredentialsException;
import com.example.deployHub_backend.repo.UserRepo;
import com.example.deployHub_backend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public void register(RegisterRequest request){
        if(userRepo.findByEmail(request.getEmail()).isPresent()){
            throw new EmailAlreadyExistsException("Email already exists");
        }
        Users user = Users.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        userRepo.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request){
        Users user = userRepo.findByEmail(request.getEmail()).
                orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        boolean isPasswordValid = passwordEncoder.matches(request.getPassword(),
                user.getPassword());

        if(!isPasswordValid){
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtService.generateToken(user.getEmail());


        return AuthResponse.builder()
                .token(token)
                .message("Login successful")
                .build();
    }
}
