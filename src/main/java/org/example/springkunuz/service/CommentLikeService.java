package org.example.springkunuz.service;

import org.apache.el.lang.ELArithmetic;
import org.example.springkunuz.dto.CommentLikeDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.entity.CommentEntity;
import org.example.springkunuz.entity.CommentLikeEntity;
import org.example.springkunuz.enums.LikeStatus;
import org.example.springkunuz.repository.CommentLikeRepository;
import org.example.springkunuz.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentLikeService {
       @Autowired
       private CommentLikeRepository commentLikeRepository;
       @Autowired
       private CommentRepository commentRepository;
       public String created(Integer commentId, JwtDTO jwtDTO, LikeStatus status) {
           Optional<CommentEntity> byId = commentRepository.findById(commentId);
           if (byId.isEmpty()){
               return "not found comment";
           }
           if (status.equals(LikeStatus.BUMIDI)){
              CommentLikeEntity commentLikeEntity=new CommentLikeEntity();
              commentLikeEntity.setCommentId(commentId);
              commentLikeEntity.setStatus(status);
              commentLikeEntity.setProfileId(jwtDTO.getId());
              commentLikeEntity.setCreatedDate(LocalDateTime.now());
              commentLikeRepository.save(commentLikeEntity);
              return "created Dislike";
           }
           else if (status.equals(LikeStatus.ZUR)){
               CommentLikeEntity commentLikeEntity=new CommentLikeEntity();
               commentLikeEntity.setCommentId(commentId);
               commentLikeEntity.setStatus(status);
               commentLikeEntity.setProfileId(jwtDTO.getId());
               commentLikeEntity.setCreatedDate(LocalDateTime.now());
               commentLikeRepository.save(commentLikeEntity);
               return "created like";
           }
         return "not found error";
       }

    public String deletedById(Integer commentId) {
        List<CommentLikeEntity> allByCommentId = commentLikeRepository.findAllByCommentId(commentId);
        for (CommentLikeEntity commentLikeEntity:allByCommentId){
            commentLikeRepository.delete(commentLikeEntity);
        }
        return "deleted comment like";
    }
}
