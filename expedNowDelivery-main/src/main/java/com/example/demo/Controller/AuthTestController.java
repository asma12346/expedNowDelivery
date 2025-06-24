package com.example.demo.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthTestController {

    @GetMapping("/me")
    public String getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return "Connecté en tant que : " + userDetails.getUsername();
    }
}


