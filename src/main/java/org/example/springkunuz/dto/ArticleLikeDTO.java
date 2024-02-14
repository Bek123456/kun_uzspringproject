package org.example.springkunuz.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.springkunuz.enums.LikeStatus;

@Setter
@Getter
public class ArticleLikeDTO {
    private String articleId;
    private LikeStatus status;
}
