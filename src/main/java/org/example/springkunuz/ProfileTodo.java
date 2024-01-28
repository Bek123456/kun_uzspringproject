package org.example.springkunuz;

import org.example.springkunuz.dto.ProfileDTO;
import org.example.springkunuz.entity.ProfileEntity;

public class ProfileTodo {
    public static ProfileDTO getProfile(ProfileEntity profileEntity){
        ProfileDTO profileDTO=new ProfileDTO();
        profileDTO.setId(profileDTO.getId());
        profileDTO.setName(profileDTO.getName());
        profileDTO.setSurname(profileEntity.getSurname());
        profileDTO.setEmail(profileDTO.getEmail());
        profileDTO.setPassword(profileEntity.getPassword());
        return profileDTO;
    }
}
