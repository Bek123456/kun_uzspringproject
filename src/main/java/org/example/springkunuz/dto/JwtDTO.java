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
}
