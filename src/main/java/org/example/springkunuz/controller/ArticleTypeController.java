package org.example.springkunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springkunuz.dto.ArticleTypeDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.enums.AppLanguage;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.service.ArticleTypeService;
import org.example.springkunuz.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articletype")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;
    @PostMapping("/adm")
    public ResponseEntity<String>create(@RequestBody ArticleTypeDTO articleTypeDTO,
                                        HttpServletRequest request){
      JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(articleTypeService.create(articleTypeDTO));
    }
    @PutMapping("/adm/articleTypeedit/{id}")
    public ResponseEntity<String>edit1(@PathVariable Integer id,
                                       @RequestBody ArticleTypeDTO articleTypeDTO,
                                       HttpServletRequest request){
//        JwtDTO jwtDTO= JWTUtil.decode(jwt);
//        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }

        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(articleTypeService.edit1(id,articleTypeDTO));
    }
    @DeleteMapping("/adm/articletypedelet/{id}")
    public ResponseEntity<String>deleted(@PathVariable Integer id,
                                         HttpServletRequest request){
        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
        String deleted = articleTypeService.deleted(id);
        return ResponseEntity.ok(deleted);
    }
    @GetMapping("/adm/pagination")
    public ResponseEntity<PageImpl<ArticleTypeDTO>>getPage(@RequestParam(value = "page")Integer page,
                                                           @RequestParam(value = "size")Integer size,
                                                          HttpServletRequest request){
        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(articleTypeService.getPage(page,size));
    }
    @GetMapping("/adm/byLang")
    public ResponseEntity<List<ArticleTypeDTO>>getByLang(@RequestParam(value = "lang", defaultValue = "uz")
                                                             AppLanguage language,
                                                            HttpServletRequest request){
          JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
         return ResponseEntity.ok(articleTypeService.getByLang(language));
    }

}
