package org.example.springkunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springkunuz.config.CustomUserDetails;
import org.example.springkunuz.config.SpringSecurityConfig;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.dto.RegionDTO;
import org.example.springkunuz.enums.AppLanguage;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.service.RegionService;
import org.example.springkunuz.util.HttpRequestUtil;
import org.example.springkunuz.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
   public class RegionController {
       @Autowired
       private RegionService regionService;

       @PostMapping("/adm")
       public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO regionDTO) {
//           Integer profileId = HttpRequestUtil.getProfileId(request,ProfileRole.ADMIN);
           String crated = regionService.crated(regionDTO);
           CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
           return ResponseEntity.ok(regionDTO);
       }

       @PutMapping("/adm/{id}")
       public ResponseEntity<String> edit(@RequestBody RegionDTO regionDTO,
                                          @PathVariable Integer id) {

//            JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
//           JwtDTO jwtDTO = JWTUtil.decode(jwt);
//           if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
//               return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//           }

           return ResponseEntity.ok(regionService.edit(id,regionDTO));
       }

       @DeleteMapping("/adm/{deletbyId}")
       public ResponseEntity<String>deleted(@PathVariable Integer deletbyId){
//           JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);

//          ProfileRole role=(ProfileRole) request.getAttribute("role");
//          if (!role.equals(ProfileRole.ADMIN)){
//              return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//          }
           return ResponseEntity.ok(regionService.deletedById(deletbyId));
       }
       @GetMapping("/adm/all")
       public ResponseEntity<List<RegionDTO>>getAll(HttpServletRequest request){
//           JwtDTO jwtDTO = JWTUtil.decode(jwt);
//           if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
//               return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//           }
//           JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
           return ResponseEntity.ok(regionService.getAll());
       }
       @GetMapping("/getLang")
       public ResponseEntity<List<RegionDTO>>getLang(@RequestParam(value = "lang")AppLanguage language){
           return ResponseEntity.ok(regionService.getLanguche(language));
       }
       @GetMapping("/change")
       @PreAuthorize("hasRole('ADMIN')")
       public ResponseEntity<String>change(){
           return ResponseEntity.ok("DONE");
       }
      @GetMapping("/change2")
      @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ResponseEntity<String> change2() {
        return ResponseEntity.ok("DONE");
    }


   }
