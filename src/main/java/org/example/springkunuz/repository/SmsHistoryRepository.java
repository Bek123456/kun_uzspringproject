package org.example.springkunuz.repository;

import org.example.springkunuz.entity.SmsHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SmsHistoryRepository extends
        CrudRepository<SmsHistoryEntity,Integer>,
        PagingAndSortingRepository<SmsHistoryEntity,Integer> {


}
