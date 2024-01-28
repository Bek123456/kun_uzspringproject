package org.example.springkunuz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "article_type")
@Setter
@Getter
public class ArticleTypeEntity extends BaseEntity {

    @Column(name = "order_name")
    private String orderNumber;
    @Column(name = "name_uz")
    private String nameUz;
    @Column(name = "name_rus")
    private String nameRus;
    @Column(name = "name_eng")
    private String nameEng;

}
