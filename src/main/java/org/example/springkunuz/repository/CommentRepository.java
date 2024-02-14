package org.example.springkunuz.repository;

import org.example.springkunuz.entity.CommentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends CrudRepository<CommentEntity,Integer>,
        PagingAndSortingRepository<CommentEntity,Integer> {
    Optional<CommentEntity>findAllByArticleId(String articleId);
    List<CommentEntity>findAllByProfileId(Integer profileId);
    List<CommentEntity>findAllByArticleIdAndProfileId(String articleId, Integer profileId);

}
