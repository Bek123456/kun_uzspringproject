package org.example.springkunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springkunuz.config.CustomUserDetails;
import org.example.springkunuz.dto.ArticleLikeDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.service.ArticleLikeService;
import org.example.springkunuz.util.HttpRequestUtil;
import org.example.springkunuz.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articlelike")
public class ArticleLikeController {

    @Autowired
    private ArticleLikeService articleLikeService;


    @PostMapping("/created")
    public ResponseEntity<String>created(@RequestBody ArticleLikeDTO articleLikeDTO){

//        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request, ProfileRole.ROLE_ADMIN,ProfileRole.ROLE_MODERATOR,ProfileRole.ROLE_USER);
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        String created = articleLikeService.created(customUserDetails.getId(),articleLikeDTO);
        return ResponseEntity.ok(created);
    }
    @PostMapping("/createdDislaik")
    public ResponseEntity<String>createdDislike(@RequestBody ArticleLikeDTO articleLikeDTO){
//        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request, ProfileRole.ROLE_ADMIN,ProfileRole.ROLE_MODERATOR,ProfileRole.ROLE_USER);
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(articleLikeService.createdDislike(articleLikeDTO,customUserDetails.getId()));
    }
    @DeleteMapping("/deleted/{articleId}")
    public ResponseEntity<String>deletedById(@PathVariable String articleId,
                                             HttpServletRequest request){
//        JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_USER,ProfileRole.ROLE_MODERATOR,ProfileRole.ROLE_ADMIN);
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(articleLikeService.deleted(articleId,customUserDetails.getId()));
    }

}
