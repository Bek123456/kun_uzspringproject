package org.example.springkunuz.controller.registr;

import org.example.springkunuz.dto.ProfileDTO;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.exp.AppBadException;
import org.example.springkunuz.service.registr.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private RegisterService registerService;
    @PostMapping("/create")
    public ResponseEntity<String>created(@RequestBody ProfileDTO profileDTO){
        if (profileDTO.getRole().equals(ProfileRole.ADMIN)){
            throw new AppBadException("Buni iloji yoq");
        }
        return ResponseEntity.ok(registerService.creared(profileDTO));
    }

}
