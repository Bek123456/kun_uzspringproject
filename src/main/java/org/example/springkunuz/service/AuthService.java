package org.example.springkunuz.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.example.springkunuz.dto.AuthDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.dto.ProfileDTO;
import org.example.springkunuz.entity.ProfileEntity;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.exp.AppBadException;
import org.example.springkunuz.repository.ProfileRepository;
import org.example.springkunuz.util.JWTUtil;
import org.example.springkunuz.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Optional;
@Service
public class AuthService {
     @Autowired
     private ProfileRepository profileRepository;
     public ProfileDTO auth(AuthDTO profile){
          Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(profile.getEmail(),
                  MDUtil.encode(profile.getPassword()));

          if (optional.isEmpty()) {
               throw new AppBadException("Email or Password is wrong");
          }
          ProfileEntity entity = optional.get();
          ProfileDTO dto = new ProfileDTO();
          dto.setName(entity.getName());
          dto.setSurname(entity.getSurname());
          dto.setRole(entity.getRole());
          dto.setJwt(JWTUtil.encode(entity.getId(),entity.getRole()));
          return dto;
     }

}
