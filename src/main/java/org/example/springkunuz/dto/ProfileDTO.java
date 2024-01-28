package org.example.springkunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.enums.ProfileStatus;

import java.time.LocalDateTime;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileDTO {
    protected Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
    private String phone;
    protected LocalDateTime createdDate;
    protected LocalDateTime updatedDate;
    private Boolean visible;
    private String jwt;
}
