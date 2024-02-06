package org.example.springkunuz.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "article")
public class ArticleEntity  {
    @Id
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "content")
    private String content;
    @Column(name = "shared_count")
    private Integer sharedCount;
    @OneToMany
    private List<AttachEntity>attachEntityList;
    @ManyToOne
    private RegionEntity region;
    @ManyToOne
    private CategoryEntity categoryEntities;
    @ManyToOne
    private ProfileEntity moderatorId;
    @ManyToOne
    private ProfileEntity publisherId;
    @Column(name = "published_date")
    private LocalDateTime publishedDate;
    @Column(name = "view_count")
    private Integer viewCount;
    @OneToMany
    private List<ArticleTypeEntity>articleTypeEntities;
}
