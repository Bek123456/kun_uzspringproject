package org.example.springkunuz.repository;

import org.example.springkunuz.entity.EmailSendHistoryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmailSendHistoryRepository
        extends CrudRepository<EmailSendHistoryEntity,Integer>,
        PagingAndSortingRepository<EmailSendHistoryEntity,Integer> {


}
