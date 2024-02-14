package org.example.springkunuz.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.springkunuz.enums.LikeStatus;

@Getter
@Setter
public class CommentLikeDTO {
    private LikeStatus status;
}
