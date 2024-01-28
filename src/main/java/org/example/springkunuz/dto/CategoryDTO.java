package org.example.springkunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoryDTO {
    private Integer id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private Boolean visible = true;
    private String nameUz;
    private String nameEng;
    private String nameRu;
    private String name;
    private Integer orderNumber;
}
