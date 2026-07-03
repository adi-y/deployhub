package com.example.deployHub_backend.service.impl;

import com.example.deployHub_backend.entity.Users;
import com.example.deployHub_backend.repo.UserRepo;
import com.example.deployHub_backend.service.CurrentUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserServiceImpl implements CurrentUserDetailService {
    private final UserRepo userRepo;

    @Override
    public Users getCurrentUser(){
        Authentication authentication= SecurityContextHolder
                .getContext()
                .getAuthentication();
        String email = authentication.getName();

        return userRepo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
    }
}
