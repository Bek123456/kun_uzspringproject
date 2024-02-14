package org.example.springkunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springkunuz.dto.ArticleCreateDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.service.ArticleService;
import org.example.springkunuz.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;
    @PostMapping("/adm/create")
    public ResponseEntity<String>create(@RequestBody ArticleCreateDTO dto,
                                        HttpServletRequest request){
        Integer moderatorId = HttpRequestUtil.getProfileId(request, ProfileRole.ROLE_MODERATOR);
        return ResponseEntity.ok(articleService.create(dto, moderatorId));
    }
    @PutMapping("/adm/edit/{articleId}")
    public ResponseEntity<String>edit(@RequestBody ArticleCreateDTO dto,
                                      @PathVariable(value = "articleId") String articleId,
                                      HttpServletRequest request){
        JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN,ProfileRole.ROLE_MODERATOR);
        String edit = articleService.edit(dto, articleId, jwtDTO);
        return ResponseEntity.ok(edit);
    }
   @DeleteMapping("/adm/deleted/{articleId}")
    public ResponseEntity<String>deletedById(@PathVariable String articleId,
                                             HttpServletRequest request){
        JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN,ProfileRole.ROLE_MODERATOR);
        return ResponseEntity.ok(articleService.deleted(articleId, jwtDTO.getId()));
   }

   @PutMapping("/adm/status/{articleId}")
    public ResponseEntity<String>editStatusById(@PathVariable String articleId,
                                                HttpServletRequest request){
        JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN,ProfileRole.ROLE_MODERATOR);
        return ResponseEntity.ok(articleService.editStatusById(articleId,jwtDTO.getId()));
   }
}
