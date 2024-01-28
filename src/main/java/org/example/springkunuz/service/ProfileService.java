package org.example.springkunuz.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.springkunuz.ProfileTodo;
import org.example.springkunuz.dto.PaginationResultDTO;
import org.example.springkunuz.dto.ProfileDTO;
import org.example.springkunuz.dto.ProfileFilter;
import org.example.springkunuz.entity.ProfileEntity;
import org.example.springkunuz.exp.AppBadException;
import org.example.springkunuz.repository.ProfileRepository;
import org.example.springkunuz.util.MDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;



@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EntityManager entityManager;

    public String created(ProfileDTO profileDTO) {
        ProfileEntity profileEntity=new ProfileEntity();
        profileEntity.setName(profileDTO.getName());
        profileEntity.setSurname(profileDTO.getSurname());
        profileEntity.setStatus(profileDTO.getStatus());
        profileEntity.setPassword(MDUtil.encode(profileDTO.getPassword()));
        profileEntity.setRole(profileDTO.getRole());
        profileEntity.setEmail(profileDTO.getEmail());
        profileEntity.setPhone(profileDTO.getPhone());
        profileRepository.save(profileEntity);
        return "created Profile";
    }

    public String edit(Integer id, ProfileDTO profileDTO) {
        Optional<ProfileEntity> optionalProfile =
                profileRepository.findById(id);

        if (optionalProfile.isEmpty()){
            throw new AppBadException("Bunday profile mavjud emas");
        }

        ProfileEntity profileEntity = optionalProfile.get();
        profileEntity.setSurname(profileDTO.getSurname());
        profileEntity.setName(profileDTO.getName());
        profileEntity.setPhone(profileDTO.getPhone());
        profileEntity.setRole(profileDTO.getRole());
        System.out.println(profileEntity);
        profileRepository.save(profileEntity);
        return "edit status";
    }

    public String editDetail(Integer id, ProfileDTO profileDTO) {
        Optional<ProfileEntity> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isEmpty()){
            throw new AppBadException("Not found profile");
        }
        ProfileEntity profileEntity = optionalProfile.get();
        profileEntity.setName(profileDTO.getName());
        profileEntity.setSurname(profileDTO.getSurname());
        profileEntity.setStatus(profileDTO.getStatus());
        profileEntity.setPassword(profileDTO.getPassword());
        profileEntity.setRole(profileDTO.getRole());
        profileEntity.setEmail(profileDTO.getEmail());
        profileEntity.setPhone(profileDTO.getPhone());
        profileRepository.save(profileEntity);
        return "edit profile";
    }

     public PageImpl<ProfileDTO> getAllPage(Integer page, Integer size) {
         Pageable pageable= PageRequest.of(page-1,size);
         Page<ProfileEntity>page1=profileRepository.findAll(pageable);
         List<ProfileEntity>profileEntities=page1.getContent();
         List<ProfileDTO>profileDTOList=new ArrayList<>();
         for (ProfileEntity profileEntity:profileEntities){
             profileDTOList.add(ProfileTodo.getProfile(profileEntity));
         }
         Long totalSize=page1.getTotalElements();
         return new PageImpl<>(profileDTOList,pageable,totalSize);
     }

    public String deleteById(Integer id) {
        profileRepository.deleteById(id);
        return "deleted Profile";
    }

    public String editPhone(Integer id, ProfileDTO profileDTO) {
        Optional<ProfileEntity> optionalProfile = profileRepository.findById(id);
        if (optionalProfile.isEmpty()){
            throw new AppBadException("Not found profile");
        }
        ProfileEntity profileEntity = optionalProfile.get();
        profileEntity.setPhone(profileDTO.getPhone());
        profileRepository.save(profileEntity);
        return "Edit profile phone";
    }

    public PaginationResultDTO<ProfileEntity> getFilter(ProfileFilter profileDTO,int page,int size) {
        StringBuilder builder = new StringBuilder();
        Map<String, Object> params = new HashMap<>();
        if (profileDTO.getName() != null) {
            builder.append("and name =:name ");
            params.put("name", profileDTO.getName());
        }

        if (profileDTO.getSurName() != null) {
            builder.append("and surname =:surname ");
            params.put("surname", profileDTO.getSurName());
        }

        if (profileDTO.getPhone() != null) {
              builder.append("and phone =:phone ");
              params.put("phone", profileDTO.getPhone());
        }

        if (profileDTO.getRole()!=null){
            builder.append("and role =:role ");
            params.put("role",profileDTO.getRole());
        }

        if (profileDTO.getCreated_date_from()!=null&&profileDTO.getCreated_date_to()!=null){
            LocalDateTime fromDate=LocalDateTime.of(profileDTO.getCreated_date_from(),LocalTime.MIN);
            LocalDateTime toDate=LocalDateTime.of(profileDTO.getCreated_date_to(),LocalTime.MAX);
            builder.append("and createdDate between :fromDate and :toDate");
            params.put("fromDate",fromDate);
            params.put("toDate",toDate);
        }
        else if (profileDTO.getCreated_date_from()!=null){
            LocalDateTime fromDate = LocalDateTime.of(profileDTO.getCreated_date_from(), LocalTime.MIN);
            LocalDateTime toDate = LocalDateTime.of(profileDTO.getCreated_date_to(), LocalTime.MAX);
            builder.append("and createdDate between :fromDate and :toDate ");
            params.put("fromDate", fromDate);
            params.put("toDate", toDate);
        }

        else if (profileDTO.getCreated_date_to()!=null){
            LocalDateTime toDate = LocalDateTime.of(profileDTO.getCreated_date_to(), LocalTime.MAX);
            builder.append("and createdDate <= :toDate ");
            params.put("toDate", toDate);
        }

        StringBuilder selectBuilder = new StringBuilder("FROM ProfileEntity s where 1=1 ");
        selectBuilder.append(builder);
        selectBuilder.append(" order by createdDate desc ");

        StringBuilder countBuilder = new StringBuilder("Select count(s) FROM ProfileEntity s where 1=1 ");
        countBuilder.append(builder);

        Query selectQuery = entityManager.createQuery(selectBuilder.toString());
        selectQuery.setMaxResults(size);//limit
        selectQuery.setFirstResult((page-1)*size);//offset(page-1)*size
        Query countQuery = entityManager.createQuery(countBuilder.toString());

        for (Map.Entry<String, Object> param : params.entrySet()) {
            selectQuery.setParameter(param.getKey(), param.getValue());
            countQuery.setParameter(param.getKey(), param.getValue());
        }
        List<ProfileEntity> entityList = selectQuery.getResultList();
        Long totalElements = (Long) countQuery.getSingleResult();
        return new PaginationResultDTO<ProfileEntity>(totalElements,entityList);
    }

}
