package org.example.springkunuz.repository;

import org.example.springkunuz.entity.ArticleEntity;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<ArticleEntity,String> {

}
