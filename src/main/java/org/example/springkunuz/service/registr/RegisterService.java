package org.example.springkunuz.service.registr;

import org.example.springkunuz.dto.ProfileDTO;
import org.example.springkunuz.entity.ProfileEntity;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.enums.ProfileStatus;
import org.example.springkunuz.repository.ProfileRepository;
import org.example.springkunuz.util.JWTUtil;
import org.example.springkunuz.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {
    @Autowired
    private ProfileRepository profileRepository;
    public String creared(ProfileDTO profileDTO) {
        ProfileEntity user = new ProfileEntity();
        user.setName(profileDTO.getName());
        user.setSurname(profileDTO.getSurname());
        user.setEmail(profileDTO.getEmail());
        user.setStatus(profileDTO.getStatus());
        user.setRole(profileDTO.getRole());
        user.setPhone(profileDTO.getPhone());
        user.setPassword(MDUtil.encode(profileDTO.getPassword()));
        profileRepository.save(user);
        return "Registeration user";
    }
}
