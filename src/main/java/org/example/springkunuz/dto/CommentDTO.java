package org.example.springkunuz.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.example.springkunuz.entity.ArticleEntity;
import org.example.springkunuz.entity.CommentEntity;
import org.example.springkunuz.entity.ProfileEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {
    private Integer commentId;
    private String content;
    private Integer replyId;
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;
    private Integer profileId;
    private String name;
    private String surname;
    private String articleId;
    private String title;


}
