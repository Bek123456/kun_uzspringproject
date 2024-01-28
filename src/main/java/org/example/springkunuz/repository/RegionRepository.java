package org.example.springkunuz.repository;

import org.example.springkunuz.entity.RegionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RegionRepository extends CrudRepository<RegionEntity,Integer>,
        PagingAndSortingRepository<RegionEntity,Integer> {
    Page<RegionEntity> findAllByVisible(Pageable pageable, boolean b);

}
