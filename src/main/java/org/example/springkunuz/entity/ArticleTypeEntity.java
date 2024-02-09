package org.example.springkunuz.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "article_type")
@Data
public class ArticleTypeEntity extends BaseEntity{
    private String order_number;
    private String nameUz;
    private String nameEng;
    private String nameRus;
}
