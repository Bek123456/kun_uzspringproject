package org.example.springkunuz.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.springkunuz.dto.CategoryDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.enums.AppLanguage;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.service.CategoryService;
import org.example.springkunuz.util.HttpRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
      @Autowired
      private CategoryService categoryService;

    @PostMapping("/adm/created")
    public ResponseEntity<String>created(@RequestBody CategoryDTO categoryDTO){
//                JwtDTO jwtDTO= HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
                return ResponseEntity.ok(categoryService.created(categoryDTO));
    }
    @PutMapping("/adm/{byId}")
    public ResponseEntity<String>editById(@PathVariable Integer byId,
                                          @RequestBody CategoryDTO categoryDTO){
//       JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(categoryService.editById(byId,categoryDTO));
    }
    @DeleteMapping("/adm/{deletedById}")
    public ResponseEntity<String>deleted(@PathVariable Integer deletedById){
//        JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok(categoryService.deletedById(deletedById));
    }
    @GetMapping("/adm/getAllOrderNumber")
    public ResponseEntity<List<CategoryDTO>>getAllOrderNumber(@RequestParam(value = "order_number")
                                                                  Integer order_number){
//        JwtDTO jwtDTO=HttpRequestUtil.getJWTDTO(request,ProfileRole.ROLE_ADMIN);
        List<CategoryDTO> allOrderNumber = categoryService.getAllOrderNumber(order_number);
        return ResponseEntity.ok(allOrderNumber);
    }
    @GetMapping("/byLang")
    public ResponseEntity<List<CategoryDTO>>getByLang(@RequestParam(value = "lang")AppLanguage language){
        List<CategoryDTO> categoryDTOList = categoryService.getByLang(language);
        return ResponseEntity.ok(categoryDTOList);
    }


}
