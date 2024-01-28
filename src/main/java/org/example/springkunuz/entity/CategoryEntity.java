package org.example.springkunuz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "categoty")
@Setter
@Getter
public class CategoryEntity extends BaseEntity {
      @Column(name = "name_uz")
      private String nameUz;
      @Column(name = "name_eng")
      private String nameEng;
      @Column(name = "name_ru")
      private String nameRu;
      @Column(name = "order_number")
      private Integer orderNumber;
}
