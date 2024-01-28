package org.example.springkunuz.service;

import org.example.springkunuz.dto.RegionDTO;
import org.example.springkunuz.entity.RegionEntity;
import org.example.springkunuz.enums.AppLanguage;
import org.example.springkunuz.exp.AppBadException;
import org.example.springkunuz.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {
    @Autowired
    public RegionRepository regionRepository;

    public String crated(RegionDTO regionDTO) {
        RegionEntity regionEntity=new RegionEntity();
        regionEntity.setOrderNumber(regionDTO.getOrderNumber());
        regionEntity.setNameEn(regionDTO.getNameEn());
        regionEntity.setNameRu(regionDTO.getNameRu());
        regionEntity.setNameUz(regionDTO.getNameUz());
        regionEntity.setVisible(true);
        regionEntity.setCreatedDate(LocalDateTime.now());
        regionRepository.save(regionEntity);
        return "add region";
    }

    public String edit(Integer id, RegionDTO regionDTO) {
        Optional<RegionEntity> optionalRegion = regionRepository.findById(id);
        if (optionalRegion.isEmpty()){
            throw new AppBadException("Not found region");
        }
        RegionEntity regionEntity = optionalRegion.get();
        regionEntity.setNameEn(regionDTO.getNameEn());
        regionEntity.setNameRu(regionDTO.getNameRu());
        regionEntity.setNameUz(regionDTO.getNameUz());
        regionEntity.setOrderNumber(regionDTO.getOrderNumber());

        regionRepository.save(regionEntity);
        return "edit region";
    }

       public String deletedById(Integer deletbyId) {
           Optional<RegionEntity> optionalRegion = regionRepository.findById(deletbyId);
           if (optionalRegion.isEmpty()){
               throw new AppBadException("Not found region");
           }
           regionRepository.deleteById(deletbyId);
           return "deleted region";
       }

    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> regionRepositoryAll = regionRepository.findAll();
        List<RegionDTO>regionDTOList=new ArrayList<>();

        for (RegionEntity regionEntity:regionRepositoryAll){
            RegionDTO regionDTO=new RegionDTO();
            regionDTO.setNameUz(regionEntity.getNameUz());
            regionDTO.setNameRu(regionEntity.getNameRu());
            regionDTO.setNameEn(regionEntity.getNameEn());
            regionDTO.setId(regionEntity.getId());
            regionDTO.setVisible(regionEntity.getVisible());
            regionDTO.setCreatedDate(regionEntity.getCreatedDate());
            regionDTOList.add(regionDTO);
        }
        return regionDTOList;
    }

    public List<RegionDTO> getLanguche(AppLanguage language) {
        Iterable<RegionEntity> regionRepositoryAll = regionRepository.findAll();
        List<RegionDTO>regionDTOList=new ArrayList<>();
        for (RegionEntity regionEntity:regionRepositoryAll){
            RegionDTO regionDTO=new RegionDTO();
            regionDTO.setId(regionEntity.getId());
            switch (language){
                case ru:
                    regionDTO.setName(regionEntity.getNameRu());
                    break;
                case eng:
                    regionDTO.setName(regionEntity.getNameEn());
                    break;
                default:
                    regionDTO.setName(regionEntity.getNameUz());
                    break;
            }
            regionDTOList.add(regionDTO);
        }

        return regionDTOList;
    }


//    public RegionDTO create(RegionDTO dto) {
//        RegionEntity entity = new RegionEntity();
//
//        entity.setNameUz(dto.getNameUz());
//        entity.setNameRu(dto.getNameRu());
//        entity.setNameEn(dto.getNameEn());
//        entity.setVisible(true);
//        entity.setOrderNumber(dto.getOrderNumber());
//        entity.setCreatedDate(LocalDateTime.now());
//
//        regionRepository.save(entity);
//
//        dto.setId(entity.getId());
//
//        dto.setCreatedDate(entity.getCreatedDate());
//        return dto;
//    }
//
//
//    public RegionDTO findById(Integer id) {
//        Optional<RegionEntity> optional = regionRepository.findById(id);
//        if (optional.isEmpty()) {
//            throw new AppBadException("Region not found❌❌❌");
//        }
//
//        return toDTO(optional.get());
//
//    }
//
//
//    public Boolean updateById(Integer id, RegionDTO dto) {
//        Optional<RegionEntity> optional = regionRepository.findById(id);
//        if (optional.isEmpty()) {
//            throw new AppBadException("Region not found❌❌❌");
//        }
//
//        RegionEntity entity = optional.get();
//
//        entity.setOrderNumber(dto.getOrderNumber());
//        entity.setNameUz(dto.getNameUz());
//        entity.setNameRu(dto.getNameRu());
//        entity.setNameEn(dto.getNameEn());
//        entity.setVisible(dto.getVisible());
//
//        regionRepository.save(entity);
//        return true;
//    }
//
//
//    public Boolean deleteById(Integer id) {
//
//        Optional<RegionEntity> optional = regionRepository.findById(id);
//        if (optional.isEmpty()) {
//            throw new AppBadException("Region not found❌❌❌");
//        }
//
//        regionRepository.deleteById(optional.get().getId());
//        return true;
//    }
//
//    public PageImpl<RegionDTO> pagination(Integer page, Integer size) {
//        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
//
//        Pageable pageable = PageRequest.of(page - 1, size, sort);
//
//        Page<RegionEntity> entityPage = regionRepository.findAllByVisible(pageable, true);
//
//        List<RegionEntity> content = entityPage.getContent();
//        long totalElements = entityPage.getTotalElements();
//
//        List<RegionDTO> dtoList = new LinkedList<>();
//        for (RegionEntity entity : entityPage) {
//            dtoList.add(toDTO(entity));
//        }
//
//        return new PageImpl<>(dtoList, pageable, totalElements);
//    }
//
//
//    public List<RegionDTO> getByLang( AppLanguage keyLang) {
//        List<RegionDTO> dtoList=new LinkedList<>();
//        Iterable<RegionEntity> all = regionRepository.findAll();
//
//        for (RegionEntity entity : all) {
//            RegionDTO dto = new RegionDTO();
//            dto.setId(entity.getId());
//            switch (keyLang) {
//                case uz ->dto.setName(entity.getNameUz());
//                case ru -> dto.setName(entity.getNameRu());
//                default ->
//                        dto.setName( entity.getNameEn());
//            };
//            dtoList.add(dto);
//        }
//        return dtoList;
//    }
//
//
//    public RegionDTO toDTO(RegionEntity entity) {
//        RegionDTO dto = new RegionDTO();
//
//        dto.setId(entity.getId());
//        dto.setOrderNumber(entity.getOrderNumber());
//        dto.setNameUz(entity.getNameUz());
//        dto.setNameRu(entity.getNameRu());
//        dto.setNameEn(entity.getNameEn());
//        dto.setCreatedDate(entity.getCreatedDate());
//        dto.setUpdatedDate(entity.getUpdatedDate());
//        dto.setVisible(entity.getVisible());
//
//        return dto;
//    }



}
