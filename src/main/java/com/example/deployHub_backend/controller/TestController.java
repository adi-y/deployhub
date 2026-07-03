package com.example.deployHub_backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @GetMapping
    public String test() {
        return "JWT Working";
    }

    @GetMapping("/whoami")
    public String whoAmI(
            Authentication authentication
    ) {

        return authentication.getName();
    }
}


