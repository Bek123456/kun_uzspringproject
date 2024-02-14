package org.example.springkunuz.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.example.springkunuz.dto.AuthDTO;
import org.example.springkunuz.dto.ProfileDTO;
import org.example.springkunuz.dto.RegistrationDTO;
import org.example.springkunuz.service.AuthService;
import org.example.springkunuz.service.MailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@Tag(name = "Authorization Api list", description = "Api list for Authorization")
@RequestMapping("/api/auth")

public class AuthController {
    @Autowired
    private AuthService authService;
    //private Logger log= LoggerFactory.getLogger(AuthController.class);
    @PostMapping("/login")
    @Operation( summary = "Api for login", description = "this api used for authorization")
    public ResponseEntity<ProfileDTO>login(HttpServletRequest request, @RequestBody  AuthDTO auth){
        log.trace("Login In Trace");
        log.debug("Login In Debug");
        log.info("Login {}",auth.getEmail());
        log.warn("Login {}",auth.getEmail());
        log.error("Login {}",auth.getEmail());
        ProfileDTO autht = authService.auth(auth);
        return ResponseEntity.ok(autht);
    }
    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDTO dto) {
        log.info("registration {} ",dto.getEmail());
        return ResponseEntity.ok(authService.registration(dto));
    }

    @GetMapping("/verification/email/jwt")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt) {
        log.info("emailVerification {} ",jwt);
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }
    @GetMapping("/verification/sms/jwt")
    public ResponseEntity<String> smsVerification(@PathVariable("jwt") String jwt) {
        String s = authService.smsVerification(jwt);
        return ResponseEntity.ok(s);
    }

}
