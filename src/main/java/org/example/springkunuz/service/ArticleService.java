package org.example.springkunuz.service;

import org.example.springkunuz.dto.ArticleCreateDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.entity.ArticleEntity;
import org.example.springkunuz.entity.ArticleTypeEntity;
import org.example.springkunuz.enums.ArticleStatus;
import org.example.springkunuz.repository.ArticleRepository;
import org.example.springkunuz.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ArticleTypeRepository articleTypeRepository;
    public String create(ArticleCreateDTO dto, Integer profileId) {
        if (dto.getContent()==null){
            return "content null";
        }
        if (dto.getDescription()==null){
            return "description null";
        }
        if (dto.getTitle()==null){
            return "title null";
        }
        if (dto.getPhotoId()==null){
            return "photo null";
        }
        if (dto.getRegionId()==null){
            return "region null";
        }
        if (dto.getCategoryId()==null){
            return "category null ";
        }
        if (dto.getArticleType().size()==0){
            return "article null";
        }

        ArticleEntity articleEntity=new ArticleEntity();
        articleEntity.setContent(dto.getContent());
        articleEntity.setDescription(dto.getDescription());
        articleEntity.setTitle(dto.getTitle());
        articleEntity.setPhotoId(dto.getPhotoId());
        articleEntity.setRegionId(dto.getRegionId());
        articleEntity.setCategoryId(dto.getCategoryId());
        articleEntity.setModeratorId(profileId);
        List<ArticleTypeEntity>articleTypeEntities=new ArrayList<>();
        for (Integer id: dto.getArticleType()){
            Optional<ArticleTypeEntity> optionalArticleType =
                    articleTypeRepository.findById(id);
            optionalArticleType.ifPresent(articleTypeEntities::add);
        }
        articleEntity.setArticleTypeEntities(articleTypeEntities);
        articleRepository.save(articleEntity);
        return "created article";
    }

    public String edit(ArticleCreateDTO dto, String articleId, JwtDTO jwtDTO){
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isEmpty()){
            return "not found article";
        }
        ArticleEntity articleEntity = optionalArticle.get();
        if (!articleEntity.getModeratorId().equals(jwtDTO.getId())){
           return "not found moderator";
        }
        articleEntity.setContent(dto.getContent());
        articleEntity.setDescription(dto.getDescription());
        articleEntity.setTitle(dto.getTitle());
        articleEntity.setPhotoId(dto.getPhotoId());
        articleEntity.setRegionId(dto.getRegionId());
        articleEntity.setCategoryId(dto.getCategoryId());
        articleEntity.setSharedCount(articleEntity.getSharedCount()+1);
        articleRepository.save(articleEntity);
        return "edit article";
    }
      public String deleted(String articleId,Integer moderatorId){
          Optional<ArticleEntity> optionalArticle = articleRepository.findById(articleId);
          if (optionalArticle.isEmpty()){
              return "not found article";
          }
          ArticleEntity articleEntity = optionalArticle.get();
          if (!articleEntity.getModeratorId().equals(moderatorId)){
              return "not found moderator";
          }
          articleRepository.deleteById(articleId);
          return "deleted article";
      }

    public String editStatusById(String articleId, Integer id) {
        Optional<ArticleEntity> optionalArticle = articleRepository.findById(articleId);
        if (optionalArticle.isEmpty()){
            return "not found article";
        }
        ArticleEntity articleEntity = optionalArticle.get();
        if (!articleEntity.getModeratorId().equals(id)){
            return "not found moderator";
        }
        if (articleEntity.getStatus().equals(ArticleStatus.NotPublished)){
            articleEntity.setStatus(ArticleStatus.Published);
        }
        else if (articleEntity.getStatus().equals(ArticleStatus.Published)){
            articleEntity.setStatus(ArticleStatus.NotPublished);
        }
        articleRepository.save(articleEntity);
        return "edit status article";
    }


}
