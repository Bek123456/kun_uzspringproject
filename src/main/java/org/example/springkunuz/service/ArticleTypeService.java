package org.example.springkunuz.service;

import org.example.springkunuz.dto.ArticleTypeDTO;
import org.example.springkunuz.entity.ArticleTypeEntity;
import org.example.springkunuz.enums.AppLanguage;
import org.example.springkunuz.exp.AppBadException;
import org.example.springkunuz.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;
    public String create(ArticleTypeDTO articleTypeDTO) {
        ArticleTypeEntity articleTypeEntity=new ArticleTypeEntity();
        articleTypeEntity.setNameEng(articleTypeDTO.getNameEng());
        articleTypeEntity.setNameUz(articleTypeDTO.getNameUz());
        articleTypeEntity.setNameRus(articleTypeDTO.getNameRus());
        articleTypeEntity.setOrder_number(articleTypeDTO.getOrderNumber());
        ArticleTypeEntity save = articleTypeRepository.save(articleTypeEntity);
        return "created article type";
    }

    public String edit1(Integer id, ArticleTypeDTO articleTypeDTO) {
        Optional<ArticleTypeEntity> optionalArticleType =
                articleTypeRepository.findById(id);

        if (optionalArticleType.isEmpty()){
            throw new AppBadException("Not found articleType");
        }
        ArticleTypeEntity articleTypeEntity = optionalArticleType.get();
        articleTypeEntity.setOrder_number(articleTypeDTO.getOrderNumber());
        articleTypeEntity.setNameEng(articleTypeDTO.getNameEng());
        articleTypeEntity.setNameUz(articleTypeDTO.getNameUz());
        articleTypeEntity.setNameRus(articleTypeDTO.getNameRus());
        articleTypeRepository.save(articleTypeEntity);
        return "edit1 articleType";
    }

    public String deleted(Integer id) {
        articleTypeRepository.deleteById(id);
        return "deleted by articleType";
    }

    public PageImpl<ArticleTypeDTO> getPage(Integer page, Integer size) {
        Pageable pageable= PageRequest.of(page-1,size);
        Page<ArticleTypeEntity> all = articleTypeRepository.findAll(pageable);

        List<ArticleTypeEntity>articleTypeEntities=all.getContent();

        Long totalSize=all.getTotalElements();
        List<ArticleTypeDTO>articleTypeDTOList=new ArrayList<>();
        for (ArticleTypeEntity articleTypeEntity:all){
            ArticleTypeDTO articleTypeDTO=new ArticleTypeDTO();
            articleTypeDTO.setNameUz(articleTypeEntity.getNameUz());
            articleTypeDTO.setNameRus(articleTypeEntity.getNameRus());
            articleTypeDTO.setNameEng(articleTypeEntity.getNameEng());
            articleTypeDTO.setVisible(articleTypeEntity.getVisible());
            articleTypeDTO.setId(articleTypeEntity.getId());
            articleTypeDTO.setCreatedDate(articleTypeEntity.getCreatedDate());
            articleTypeDTOList.add(articleTypeDTO);
        }
        return new PageImpl<>(articleTypeDTOList,pageable,totalSize);
    }

    public List<ArticleTypeDTO> getByLang(AppLanguage language) {
        Iterable<ArticleTypeEntity> all = articleTypeRepository.findAll();
         List<ArticleTypeDTO>articleTypeDTOList=new ArrayList<>();
         for (ArticleTypeEntity articleTypeEntity:all){
             ArticleTypeDTO articleTypeDTO=new ArticleTypeDTO();
             articleTypeDTO.setId(articleTypeEntity.getId());
             switch (language){
                 case ru :
                     articleTypeDTO.setName(articleTypeEntity.getNameRus());
                     break;
                 case uz:
                     articleTypeDTO.setName(articleTypeEntity.getNameUz());
                     break;
                 default:
                     articleTypeDTO.setName(articleTypeEntity.getNameEng());
                     break;
             }
             articleTypeDTOList.add(articleTypeDTO);
         }
        return articleTypeDTOList;
    }
}
