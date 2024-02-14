package org.example.springkunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment_entity")
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "created_date")
    private LocalDateTime createdDate=LocalDateTime.now();
    @Column(name = "update_date")
    private LocalDateTime updateDate;
    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne
    @JoinColumn(name = "profile_id",insertable = false, updatable = false)
    private ProfileEntity profile;
    @Column(name = "content",columnDefinition = "TEXT")
    private String content;
    @Column(name = "article_id")
    private String articleId;
    @ManyToOne
    @JoinColumn(name = "article_id",insertable = false, updatable = false)
    private ArticleEntity article;

    @Column(name = "visible")
    private Boolean visible = true;
    @Column(name = "reply_id")
    private Integer replyId;
    @OneToOne
    @JoinColumn(name = "reply_id",insertable = false, updatable = false)
    private CommentEntity reply;
}
