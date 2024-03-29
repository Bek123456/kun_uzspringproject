package org.example.springkunuz.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RegistrationDTO {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
}
