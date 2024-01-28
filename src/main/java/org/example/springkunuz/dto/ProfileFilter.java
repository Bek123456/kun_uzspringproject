package org.example.springkunuz.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.springkunuz.enums.ProfileRole;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
public class ProfileFilter {
    private String name;
    private String surName;
    private String phone;
    private ProfileRole role;
    private LocalDate created_date_from;
    private LocalDate created_date_to;
}
