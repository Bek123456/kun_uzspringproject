package org.example.springkunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springkunuz.config.CustomUserDetails;
import org.example.springkunuz.dto.CommentLikeDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.service.CommentLikeService;
import org.example.springkunuz.util.HttpRequestUtil;
import org.example.springkunuz.util.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/commentlike")
public class CommentLikeController {

    @Autowired
    private CommentLikeService commentLikeService;

    @PostMapping("/lice/{commentId}")
    public ResponseEntity<String>createdLike(@PathVariable Integer commentId,
                                             @RequestBody CommentLikeDTO commentLikeDTO){
//        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,
//                ProfileRole.ROLE_USER,
//                ProfileRole.ROLE_MODERATOR,
//                ProfileRole.ROLE_ADMIN);'
        CustomUserDetails customUserDetails= SpringSecurityUtil.getCurrentUser();
        return ResponseEntity.ok(commentLikeService.created(commentId,customUserDetails.getId(),commentLikeDTO.getStatus()));
    }
    @DeleteMapping("/deletedLike/{commentId}")
    public ResponseEntity<String>deleted(@PathVariable Integer commentId){
//        JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,
//                ProfileRole.ROLE_USER,
//                ProfileRole.ROLE_MODERATOR,
//                ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(commentLikeService.deletedById(commentId));
    }



}
