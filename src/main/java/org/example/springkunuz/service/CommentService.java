package org.example.springkunuz.service;

import org.example.springkunuz.dto.CommentDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.entity.ArticleEntity;
import org.example.springkunuz.entity.CommentEntity;
import org.example.springkunuz.entity.ProfileEntity;
import org.example.springkunuz.repository.ArticleRepository;
import org.example.springkunuz.repository.CommentRepository;
import org.example.springkunuz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public String created(JwtDTO jwtDTO, CommentDTO commentDTO) {

        Optional<CommentEntity> byId = commentRepository.findById(commentDTO.getReplyId());
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(commentDTO.getArticleId());
        if (optionalArticle.isEmpty()){
            return "not found article";
        }
        CommentEntity commentEntity=new CommentEntity();
        if (!byId.isEmpty()){
            commentEntity.setReplyId(commentDTO.getReplyId());
        }
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setArticleId(commentDTO.getArticleId());
        commentEntity.setProfileId(jwtDTO.getId());
        commentRepository.save(commentEntity);
        return "created comment";
    }

    public String edit(CommentDTO commentDTO, JwtDTO jwtDTO) {
        Optional<CommentEntity> allByArticleId =
                commentRepository.findAllByArticleId(commentDTO.getArticleId());
        if (allByArticleId.isEmpty()){
            return "not found article";
        }
        CommentEntity commentEntity = allByArticleId.get();
        if (!commentEntity.getProfileId().equals(jwtDTO.getId())){
            return "not found profile";
        }
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setUpdateDate(LocalDateTime.now());
        commentRepository.save(commentEntity);
        return "edit comment";
    }

    public String deleted(Integer id) {
        List<CommentEntity> allByProfileId = commentRepository.findAllByProfileId(id);
        for (CommentEntity comment:allByProfileId){
            commentRepository.delete(comment);
        }
        return "deleted comment";
    }

    public List<CommentDTO> getById(JwtDTO jwtDTO, String articleId) {
        List<CommentEntity> allByArticleIdAndProfileId = commentRepository.findAllByArticleIdAndProfileId(articleId, jwtDTO.getId());
        List<CommentDTO>commentDTOList=new ArrayList<>();
        Optional<ProfileEntity> optionalProfile = profileRepository.findById(jwtDTO.getId());
        ProfileEntity profileEntity = optionalProfile.get();
        for (CommentEntity commentEntity:allByArticleIdAndProfileId){
             CommentDTO commentDTO=new CommentDTO();

             commentDTO.setCommentId(commentEntity.getId());
             commentDTO.setCreatedDate(commentEntity.getCreatedDate());
             commentDTO.setUpdateDate(commentEntity.getUpdateDate());
             commentDTO.setProfileId(commentEntity.getProfileId());
             commentDTO.setName(profileEntity.getName());
             commentDTO.setSurname(profileEntity.getSurname());
             commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    public PageImpl<CommentDTO>getPage(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page - 1, size);
        Page<CommentEntity>commentEntityPage=commentRepository.findAll(pageable);


        Long totalSize=commentEntityPage.getTotalElements();
        List<CommentEntity>commentEntityList=commentEntityPage.getContent();
        List<CommentDTO>commentDTOList=new ArrayList<>();
        for (CommentEntity commentEntity:commentEntityList){
            CommentDTO commentDTO=new CommentDTO();
            commentDTO.setCommentId(commentEntity.getId());
            commentDTO.setCreatedDate(commentEntity.getCreatedDate());
            commentDTO.setUpdateDate(commentEntity.getUpdateDate());
            commentDTO.setProfileId(commentEntity.getProfileId());
            Optional<ProfileEntity> optionalProfile = profileRepository.findById(commentEntity.getProfileId());
            if (optionalProfile.isEmpty()){
                return null;
            }
            ProfileEntity profileEntity = optionalProfile.get();
            commentDTO.setName(profileEntity.getName());
            commentDTO.setSurname(profileEntity.getSurname());
            commentDTO.setProfileId(profileEntity.getId());
            Optional<ArticleEntity> optionalArticle = articleRepository.findById(commentEntity.getArticleId());
            if (optionalArticle.isEmpty()){
                return null;
            }
            ArticleEntity articleEntity = optionalArticle.get();
            commentDTO.setTitle(articleEntity.getTitle());
            commentDTO.setArticleId(articleEntity.getId());
            commentDTO.setReplyId(commentEntity.getReplyId());
            commentDTOList.add(commentDTO);

        }


        return new PageImpl<>(commentDTOList,pageable,totalSize);
    }

    public CommentDTO getByCommentId(Integer commentId, JwtDTO jwtDTO) {
        Optional<CommentEntity> byId = commentRepository.findById(commentId);
        if (byId.isEmpty()){
            return null;
        }
        CommentEntity commentEntity = byId.get();
        CommentDTO commentDTO=new CommentDTO();
        commentDTO.setContent(commentEntity.getContent());
        commentDTO.setArticleId(commentEntity.getArticleId());
        return commentDTO;
    }
}
