package org.example.springkunuz.util;

import org.example.springkunuz.config.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SpringSecurityUtil {
    public static CustomUserDetails getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         //        String currentPrincipalName = authentication.getName(); // username
      //        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        return userDetails;
    }
}
