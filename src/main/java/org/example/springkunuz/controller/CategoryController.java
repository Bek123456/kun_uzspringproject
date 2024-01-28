package org.example.springkunuz.controller;

import org.example.springkunuz.dto.CategoryDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.enums.AppLanguage;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.service.CategoryService;
import org.example.springkunuz.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
      @Autowired
      private CategoryService categoryService;

    @PostMapping("/created")
    public ResponseEntity<String>created(@RequestHeader(value = "Authorization")String jwt,
                                         @RequestBody CategoryDTO categoryDTO){
                JwtDTO jwtDTO= JWTUtil.decode(jwt);
                 if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
                  }
                 return ResponseEntity.ok(categoryService.created(categoryDTO));
    }
    @PutMapping("/{byId}")
    public ResponseEntity<String>editById(@PathVariable Integer byId,@RequestBody CategoryDTO categoryDTO,
                                          @RequestHeader(value = "Authorization")String jwt){

        JwtDTO jwtDTO= JWTUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(categoryService.editById(byId,categoryDTO));
    }
    @DeleteMapping("/{deletedById}")
    public ResponseEntity<String>deleted(@PathVariable Integer deletedById,
                                         @RequestHeader(value = "Authorization")String jwt){
        JwtDTO jwtDTO= JWTUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(categoryService.deletedById(deletedById));
    }
    @GetMapping("/getAllOrderNumber")
    public ResponseEntity<List<CategoryDTO>>getAllOrderNumber(@RequestParam(value = "order_number")Integer order_number,
                                                              @RequestHeader(value = "Authorization")String jwt){
        JwtDTO jwtDTO= JWTUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        List<CategoryDTO> allOrderNumber = categoryService.getAllOrderNumber(order_number);
        return ResponseEntity.ok(allOrderNumber);
    }
    @GetMapping("/getByLang")
    public ResponseEntity<List<CategoryDTO>>getByLang(@RequestParam(value = "lang")AppLanguage language){
        List<CategoryDTO> categoryDTOList = categoryService.getByLang(language);
        return ResponseEntity.ok(categoryDTOList);
    }


}
