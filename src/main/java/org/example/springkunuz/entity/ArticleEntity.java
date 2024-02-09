package org.example.springkunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.springkunuz.enums.ArticleStatus;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "article")
@Getter
@Setter
public class ArticleEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "title", nullable = false, columnDefinition = "TEXT")
    private String title;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "content", nullable = false)
    private String content;
    @Column(name = "shared_count", nullable = false)
    private Integer sharedCount = 0;
    @Column(name = "photo_id")
    private String photoId;
    @ManyToOne
    @JoinColumn(name = "photo_id", insertable = false, updatable = false)
    private AttachEntity photo;
    @Column(name = "region_id")
    private Integer regionId;
    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private RegionEntity region;
    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity categoryEntities;
    @Column(name = "moderator_id")
    private Integer moderatorId;
    @ManyToOne
    @JoinColumn(name = "moderator_id", insertable = false, updatable = false)
    private ProfileEntity moderator;
    @Column(name = "publisher_id")
    private Integer publisherId;
    @ManyToOne
    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
    private ProfileEntity publisher;
    @Column(name = "published_date")
    private LocalDateTime publishedDate;
    @Column(name = "view_count")
    private Integer viewCount = 0;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @OneToMany(fetch = FetchType.LAZY)
    private List<ArticleTypeEntity>articleTypeEntities;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ArticleStatus status=ArticleStatus.NotPublished;
}
