package org.example.springkunuz.service;

import org.example.springkunuz.dto.CategoryDTO;
import org.example.springkunuz.entity.CategoryEntity;
import org.example.springkunuz.enums.AppLanguage;
import org.example.springkunuz.exp.AppBadException;
import org.example.springkunuz.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public String created(CategoryDTO categoryDTO) {
        CategoryEntity categoryEntity=new CategoryEntity();
        categoryEntity.setOrderNumber(categoryDTO.getOrderNumber());
        categoryEntity.setNameRu(categoryDTO.getNameRu());
        categoryEntity.setNameEng(categoryDTO.getNameEng());
        categoryEntity.setNameUz(categoryDTO.getNameUz());
        categoryRepository.save(categoryEntity);
        return "add category";
    }

    public String editById(Integer byId, CategoryDTO categoryDTO) {
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(byId);
        if (optionalCategory.isEmpty()){
            throw new AppBadException("Not found category");
        }
        CategoryEntity categoryEntity1 = optionalCategory.get();
         categoryEntity1.setNameEng(categoryDTO.getNameEng());
         categoryEntity1.setNameRu(categoryDTO.getNameRu());
         categoryEntity1.setNameUz(categoryDTO.getNameUz());
         categoryEntity1.setOrderNumber(categoryDTO.getOrderNumber());
         categoryRepository.save(categoryEntity1);
         return "edit category";
    }

    public String deletedById(Integer deletedById) {
        categoryRepository.deleteById(deletedById);
        return "deleted by category";
    }

    public List<CategoryDTO> getAllOrderNumber(Integer orderNumber) {
        List<CategoryEntity> allByOrderNumber = categoryRepository.findAllByOrderNumber(orderNumber);
        List<CategoryDTO>categoryDTOList=new ArrayList<>();
        for (CategoryEntity categoryEntity:allByOrderNumber){
            CategoryDTO categoryDTO=new CategoryDTO();
            categoryDTO.setId(categoryEntity.getId());
            categoryDTO.setOrderNumber(categoryEntity.getOrderNumber());
            categoryDTO.setVisible(categoryEntity.getVisible());
            categoryDTO.setNameEng(categoryEntity.getNameEng());
            categoryDTO.setNameRu(categoryEntity.getNameRu());
            categoryDTO.setNameUz(categoryEntity.getNameUz());
            categoryDTO.setCreatedDate(categoryEntity.getCreatedDate());
            categoryDTOList.add(categoryDTO);
        }
        return categoryDTOList;
    }

    public List<CategoryDTO> getByLang(AppLanguage language) {
        Iterable<CategoryEntity> all = categoryRepository.findAll();
        List<CategoryDTO>categoryDTOList=new ArrayList<>();
        for (CategoryEntity categoryEntity:all){
            CategoryDTO categoryDTO=new CategoryDTO();
            categoryDTO.setId(categoryEntity.getId());
            switch (language){
                case uz:
                    categoryDTO.setName(categoryEntity.getNameUz());
                    break;
                case ru:
                    categoryDTO.setName(categoryEntity.getNameRu());
                    break;
                default:
                    categoryDTO.setNameEng(categoryEntity.getNameEng());
                    break;
            }
            categoryDTOList.add(categoryDTO);
        }
       return categoryDTOList;
    }
}
