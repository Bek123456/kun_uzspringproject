package org.example.springkunuz.repository;

import jakarta.transaction.Transactional;
import org.example.springkunuz.entity.ProfileEntity;
import org.example.springkunuz.enums.ProfileStatus;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,Integer>,
        PagingAndSortingRepository<ProfileEntity,Integer> {
      Optional<ProfileEntity>findByEmail(String email);
      Optional<ProfileEntity>findByEmailAndPassword(String email,String password);
      @Transactional
      @Modifying
      @Query("Update ProfileEntity  set status =?2 where id = ?1")
      void updateStatus(Integer id, ProfileStatus active);
}
