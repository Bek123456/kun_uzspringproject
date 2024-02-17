package org.example.springkunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springkunuz.config.CustomUserDetails;
import org.example.springkunuz.dto.CommentDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.service.CommentService;
import org.example.springkunuz.util.HttpRequestUtil;
import org.example.springkunuz.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/created")
    public ResponseEntity<String>created(@RequestBody CommentDTO commentDTO,
                                         HttpServletRequest request){
        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request, ProfileRole.ROLE_ADMIN,
                ProfileRole.ROLE_MODERATOR,
                ProfileRole.ROLE_USER);
           return ResponseEntity.ok(commentService.created(jwtDTO,commentDTO));

    }
    @PutMapping("/update")
    public ResponseEntity<String>edit(@RequestBody CommentDTO commentDTO,
                                      HttpServletRequest request){
        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request, ProfileRole.ROLE_ADMIN,
                ProfileRole.ROLE_MODERATOR,
                ProfileRole.ROLE_USER);
        return ResponseEntity.ok(commentService.edit(commentDTO,jwtDTO));
    }
    @DeleteMapping("/deleted")
    public ResponseEntity<String>delete(){
//        JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,
//                ProfileRole.ROLE_USER,
//                ProfileRole.ROLE_MODERATOR,
//                ProfileRole.ROLE_ADMIN);
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();

        return ResponseEntity.ok(commentService.deleted(customUserDetails.getId()));
    }
    @GetMapping("/get/{articleId}")
    public ResponseEntity<List<CommentDTO>>getById(@PathVariable String articleId,
                                       HttpServletRequest request){
        //        JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,
//                ProfileRole.ROLE_USER,
//                ProfileRole.ROLE_MODERATOR,
//                ProfileRole.ROLE_ADMIN);

        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(commentService.getById(customUserDetails.getId(),articleId));
    }
   @GetMapping("/page")
    public ResponseEntity<PageImpl<CommentDTO>>getPage(@RequestParam(value = "page") Integer page,
                                              @RequestParam(value = "size") Integer size){
//       JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,
//               ProfileRole.ROLE_ADMIN);
      return ResponseEntity.ok(commentService.getPage(page,size));
   }

   @GetMapping("/getCommentById/{commentId}")
    public ResponseEntity<CommentDTO>getByCommentId(@PathVariable Integer commentId,
                                                    HttpServletRequest request){
       JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,
               ProfileRole.ROLE_ADMIN);
       CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
       return ResponseEntity.ok(commentService.getByCommentId(commentId));
   }
}
