package org.example.springkunuz.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.example.springkunuz.enums.ProfileRole;
@Setter
@Getter
@AllArgsConstructor
public class JwtDTO {
    private Integer id;
    private ProfileRole role;
    private String email;

    public JwtDTO(Integer id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }

    public JwtDTO(ProfileRole role, String email) {
        this.role = role;
        this.email = email;
    }

    public JwtDTO(Integer id) {
        this.id = id;
    }
}
