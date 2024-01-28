package org.example.springkunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;
    @Column(name = "created_date")
    protected LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "updated_date")
    protected LocalDateTime updatedDate;
    @Column(name = "visible")
    private Boolean visible = true;
}
