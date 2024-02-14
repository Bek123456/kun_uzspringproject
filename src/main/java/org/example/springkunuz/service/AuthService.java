package org.example.springkunuz.service;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.example.springkunuz.dto.AuthDTO;
import org.example.springkunuz.dto.JwtDTO;
import org.example.springkunuz.dto.ProfileDTO;
import org.example.springkunuz.dto.RegistrationDTO;
import org.example.springkunuz.entity.ProfileEntity;
import org.example.springkunuz.enums.ProfileRole;
import org.example.springkunuz.enums.ProfileStatus;
import org.example.springkunuz.exp.AppBadException;
import org.example.springkunuz.repository.EmailSendHistoryRepository;
import org.example.springkunuz.repository.ProfileRepository;
import org.example.springkunuz.util.JWTUtil;
import org.example.springkunuz.util.MDUtil;
import org.example.springkunuz.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Optional;
@Service
@Slf4j
public class AuthService {
     @Autowired
     private ProfileRepository profileRepository;
     @Autowired
     private MailSenderService mailSenderService;
     @Autowired
     private EmailSenderHistoryService emailSenderHistoryService;
     @Autowired
     private EmailSendHistoryRepository emailSendHistoryRepository;
     @Autowired
     private SmsServerService smsServerService;
     @Autowired
     private ResourceBundleMessageSource resourceBundleMessageSource;
     public ProfileDTO auth(AuthDTO profile){
          Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(profile.getEmail(),
                  MDUtil.encode(profile.getPassword()));

          if (optional.isEmpty()) {
               resourceBundleMessageSource.getMessage("email.password.wrong",null,new Locale("en"));
               log.warn("Email or Password is wrong{}",profile.getEmail());
               throw new AppBadException("Email or Password is wrong");
          }

          ProfileEntity entity = optional.get();

          if(!entity.getStatus().equals(ProfileStatus.ACTIVE)){
               throw new AppBadException("Profile not active");
          }
          ProfileDTO dto = new ProfileDTO();
          dto.setName(entity.getName());
          dto.setSurname(entity.getSurname());
          dto.setRole(entity.getRole());
          dto.setJwt(JWTUtil.encode(entity.getEmail()




                  ,entity.getRole()));
          return dto;
     }
     public String registration(RegistrationDTO dto) {
          // validation
          // check
          Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
          if (optional.isPresent()) {
               if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
                    profileRepository.delete(optional.get());
                    // delete
                    // or
                    //send verification code (email/sms)
               } else {
                    throw new AppBadException("Email exists");
               }
          }
          LocalDateTime from=LocalDateTime.now().minusMinutes(1);
          LocalDateTime to=LocalDateTime.now();
          if (emailSendHistoryRepository.countSendEmail(dto.getEmail(), from, to) >= 3) {
               throw new AppBadException("To many attempt. Please try after 1 minute.");
          }
          // create
          ProfileEntity entity = new ProfileEntity();
          entity.setName(dto.getName());
          entity.setSurname(dto.getSurname());
          entity.setEmail(dto.getEmail());
          entity.setPassword(MDUtil.encode(dto.getPassword()));
          entity.setStatus(ProfileStatus.REGISTRATION);
          entity.setRole(ProfileRole.ROLE_USER);
          entity.setPhone(dto.getPhone());
          profileRepository.save(entity);
          String jwt=JWTUtil.encodeForEmail(entity.getId());
//          String text = "<h1 style=\"text-align: center\">Hello %s</h1>\n" +
//                  "<p style=\"background-color: indianred; color: white; padding: 30px\">To complete registration please link to the following link</p>\n" +
//                  "<a style=\" background-color: #f44336;\n" +
//                  "  color: white;\n" +
//                  "  padding: 14px 25px;\n" +
//                  "  text-align: center;\n" +
//                  "  text-decoration: none;\n" +
//                  "  display: inline-block;\" href=\"http://localhost:8080/auth/verification/email/%s\n" +
//                  "\">Click</a>\n" +
//                  "<br>\n";
//          text = String.format(text, entity.getName(), jwt);
//          mailSenderService.sendEmail(dto.getEmail(), "Complete registration", text);
//          String created = emailSenderHistoryService.created(text, dto.getEmail());
//          return "true"+created;

          String code = RandomUtil.getRandomSmsCode();
          smsServerService.send(dto.getPhone(),"\thttp://localhost:8080/api/auth/verification/sms/jwt\t", code);
          smsServerService.createSmsHistory(code, dto.getPhone());
          return "true";
     }
     public String emailVerification(String jwt) {
          try {
               JwtDTO jwtDTO = JWTUtil.decode(jwt);

               Optional<ProfileEntity> optional = profileRepository.findById(jwtDTO.getId());
               if (!optional.isPresent()) {
                    throw new AppBadException("Profile not found");
               }
               ProfileEntity entity = optional.get();
               if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                    throw new AppBadException("Profile in wrong status");
               }
               profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);
          } catch (JwtException e) {
               throw new AppBadException("Please tyre again.");
          }
          return null;
     }

     public String smsVerification(String jwt) {
          try {
               JwtDTO jwtDTO = JWTUtil.decode(jwt);

               Optional<ProfileEntity> optional = profileRepository.findById(jwtDTO.getId());
               if (!optional.isPresent()) {
                    throw new AppBadException("Profile not found");
               }
               ProfileEntity entity = optional.get();
               if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                    throw new AppBadException("Profile in wrong status");
               }
               profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);

          } catch (JwtException e) {
               throw new AppBadException("Please tyre again.");
          }
          return null;
     }
}
