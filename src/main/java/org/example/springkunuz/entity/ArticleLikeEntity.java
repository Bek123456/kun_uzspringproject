package org.example.springkunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.springkunuz.enums.ArticleStatus;
import org.example.springkunuz.enums.LikeStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "article_like_entity")
@Getter
@Setter
public class ArticleLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "article_id")
    private String articleId;
    @ManyToOne
    @JoinColumn(name = "article_id",insertable = false, updatable = false)
    private ArticleEntity article;
    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne
    @JoinColumn(name = "profile_id",insertable = false, updatable = false)
    private ProfileEntity profile;
    @Column(name = "created_date")
    private LocalDateTime createdDate=LocalDateTime.now();
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private LikeStatus status;

}
