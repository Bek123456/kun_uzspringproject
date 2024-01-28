package org.example.springkunuz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class RegionEntity extends BaseEntity{
    @Column(name = "order_number")
    private Integer orderNumber;
    @Column(name = "name_uz")
    private String nameUz;
    @Column(name = "name_ru")
    private String nameRu;
    @Column(name = "name_en")
    private String nameEn;
    @Column(name = "name")
    private String name;
}
