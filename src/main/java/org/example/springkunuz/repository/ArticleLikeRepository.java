package org.example.springkunuz.repository;

import org.example.springkunuz.entity.ArticleLikeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ArticleLikeRepository extends
        CrudRepository<ArticleLikeEntity,Integer> {
   Optional<ArticleLikeEntity>findAllByArticleId(String articleId);
}
