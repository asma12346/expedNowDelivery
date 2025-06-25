package com.example.demo.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthTestController {
  @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Tu es bien authentifié !");
    }
}


