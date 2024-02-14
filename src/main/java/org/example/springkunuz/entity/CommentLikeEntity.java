package org.example.springkunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.springkunuz.enums.LikeStatus;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment_like_entity")
@Getter
@Setter
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne
    @JoinColumn(name = "profile_id",insertable = false, updatable = false)
    private ProfileEntity profile;
    @Column(name = "comment_id")
    private Integer commentId;
    @ManyToOne
    @JoinColumn(name = "comment_id",insertable = false, updatable = false)
    private CommentEntity comment;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "status")
    private LikeStatus status;
}
