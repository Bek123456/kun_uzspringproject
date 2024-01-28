package org.example.springkunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleTypeDTO {
    protected Integer id;
    protected LocalDateTime createdDate ;
    protected LocalDateTime updatedDate;
    private Boolean visible;
    private String orderNumber;
    private String nameUz;
    private String nameRus;
    private String nameEng;
    private String name;
}
