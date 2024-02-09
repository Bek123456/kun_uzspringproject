package org.example.springkunuz.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
public class ArticleCreateDTO {
    private String title;
    private String description;
    private String content;
    private Integer regionId;
    private Integer categoryId;
    private List<Integer> articleType; //
    private String photoId;
    private Integer publisherId;
}
