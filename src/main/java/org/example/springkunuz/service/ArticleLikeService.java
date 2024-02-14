package org.example.springkunuz.service;

import org.example.springkunuz.dto.ArticleLikeDTO;
import org.example.springkunuz.entity.ArticleEntity;
import org.example.springkunuz.entity.ArticleLikeEntity;
import org.example.springkunuz.enums.ArticleStatus;
import org.example.springkunuz.enums.LikeStatus;
import org.example.springkunuz.repository.ArticleLikeRepository;
import org.example.springkunuz.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ArticleLikeService {
@Autowired
private ArticleLikeRepository articleLikeRepository;
@Autowired
private ArticleRepository articleRepository;
    public String created(Integer id, ArticleLikeDTO articleLikeDTO) {
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(articleLikeDTO.getArticleId());
        if (optionalArticle.isEmpty()){
            return "Not found Article";
        }

        ArticleLikeEntity articleLikeEntity=new ArticleLikeEntity();
        articleLikeEntity.setArticleId(articleLikeDTO.getArticleId());
        articleLikeEntity.setStatus(articleLikeDTO.getStatus());
        articleLikeEntity.setProfileId(id);
        articleLikeRepository.save(articleLikeEntity);

        return "created article like";
    }

    public String createdDislike(ArticleLikeDTO articleLikeDTO, Integer id) {
        Optional<ArticleEntity> optionalArticle =
                articleRepository.findById(articleLikeDTO.getArticleId());
        if (optionalArticle.isEmpty()){
            return "not found article";
        }
        if (!articleLikeDTO.getStatus().equals(LikeStatus.BUMIDI)){
            return "not found dislik";
        }
        ArticleLikeEntity articleLikeEntity=new ArticleLikeEntity();
        articleLikeEntity.setArticleId(articleLikeDTO.getArticleId());
        articleLikeEntity.setProfileId(id);
        articleLikeEntity.setCreatedDate(LocalDateTime.now());
        articleLikeEntity.setStatus(articleLikeDTO.getStatus());
        articleLikeRepository.save(articleLikeEntity);
        return "created dislike";
    }

    public String deleted(String articleId, Integer id) {
        Optional<ArticleLikeEntity> allByArticleId = articleLikeRepository.findAllByArticleId(articleId);
        if (allByArticleId.isEmpty()){
            return "not found article like";
        }
        ArticleLikeEntity articleLikeEntity = allByArticleId.get();
        if (articleLikeEntity.getProfileId().equals(id)){
            articleLikeRepository.delete(articleLikeEntity);
            return "deleted artikle like";
        }
        return "not found deleted";
    }

}
