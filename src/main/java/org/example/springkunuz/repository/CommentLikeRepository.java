package org.example.springkunuz.repository;

import org.example.springkunuz.entity.CommentEntity;
import org.example.springkunuz.entity.CommentLikeEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity,Integer> {

    List<CommentLikeEntity>findAllByCommentId(Integer commentId);

}
