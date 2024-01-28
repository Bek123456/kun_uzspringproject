package org.example.springkunuz.repository;

import org.example.springkunuz.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {
    List<CategoryEntity>findAllByOrderNumber(Integer orderNumber);
}
