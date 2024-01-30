package org.example.springkunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springkunuz.ProfileTodo;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.dto.PaginationResultDTO;
import org.example.springkunuz.dto.ProfileDTO;
import org.example.springkunuz.dto.ProfileFilter;
import org.example.springkunuz.entity.ProfileEntity;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.service.ProfileService;
import org.example.springkunuz.util.HttpRequestUtil;
import org.example.springkunuz.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;
    @PostMapping("/adm")
    public ResponseEntity<String>create(@RequestBody ProfileDTO profileDTO,
                                        HttpServletRequest request){
        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.created(profileDTO));
    }

    @PutMapping("/adm/{id}")
    public ResponseEntity<String>edit(@PathVariable Integer id,
                                      @RequestBody ProfileDTO profileDTO,
                                      HttpServletRequest request){
        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ADMIN);
        String edit = profileService.edit(id, profileDTO);
        return ResponseEntity.ok(edit);
    }

    @PutMapping("/adm/editDetial/{id}")
    public ResponseEntity<String>editDetail(@PathVariable Integer id,
                                            @RequestBody ProfileDTO profileDTO,
                                           HttpServletRequest request){
        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.editDetail(id,profileDTO));
    }

    @GetMapping("/adm/allPage")
    public ResponseEntity<PageImpl<ProfileDTO>>getAllPage(@RequestParam(name = "page")Integer page,
                                                          @RequestParam(name = "size")Integer size,
                                                          HttpServletRequest request){
        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getAllPage(page,size));
    }

    @DeleteMapping("/adm/{id}")
    public ResponseEntity<String>deletedId(@PathVariable Integer id,
                                          HttpServletRequest request){
        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ADMIN);
       return ResponseEntity.ok(profileService.deleteById(id));
    }
    @PutMapping("/adm/editPhone/{id}")
    public ResponseEntity<String>editPhone(@PathVariable Integer id,
                                           @RequestBody ProfileDTO profileDTO,
                                          HttpServletRequest request){
        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.editPhone(id,profileDTO));
    }
    @GetMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>>getFilter(@RequestBody ProfileFilter profileDTO,
                                                         @RequestParam(value = "page")Integer page,
                                                         @RequestParam(value = "size")Integer size){
        PaginationResultDTO<ProfileEntity> filter = profileService.getFilter(profileDTO, page, size);
        List<ProfileDTO>profileDTOList=new ArrayList<>();
           for (ProfileEntity profileEntity:filter.getProfileList()){
               profileDTOList.add(ProfileTodo.getProfile(profileEntity));
           }
        Pageable pageable= PageRequest.of(page-1,size);
           return ResponseEntity.ok(new PageImpl<>(profileDTOList,pageable, filter.getTotalSize()));
    }
}
