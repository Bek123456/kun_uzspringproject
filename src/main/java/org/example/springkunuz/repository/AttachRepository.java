package org.example.springkunuz.repository;

import org.example.springkunuz.entity.AttachEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AttachRepository extends CrudRepository<AttachEntity,String>,
        PagingAndSortingRepository<AttachEntity,String> {

}
