package org.example.springkunuz.controller;

import jakarta.persistence.Entity;
import org.example.springkunuz.dto.AuthDTO;
import org.example.springkunuz.dto.ProfileDTO;
import org.example.springkunuz.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<ProfileDTO>login(@RequestBody  AuthDTO authDTO){
        ProfileDTO auth = authService.auth(authDTO);
        return ResponseEntity.ok(auth);
    }
}