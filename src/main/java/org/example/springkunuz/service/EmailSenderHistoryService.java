package org.example.springkunuz.service;

import org.example.springkunuz.entity.EmailSendHistoryEntity;
import org.example.springkunuz.repository.EmailSendHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailSenderHistoryService {

    @Autowired
    private EmailSendHistoryRepository emailSendHistoryRepository;
    public String created(String text, String email) {
        EmailSendHistoryEntity entity=new EmailSendHistoryEntity();
        entity.setEmail(email);
        entity.setMessage(text);
        entity.setCreatedDate(LocalDateTime.now());
        emailSendHistoryRepository.save(entity);
        return "created email history";
    }

}
