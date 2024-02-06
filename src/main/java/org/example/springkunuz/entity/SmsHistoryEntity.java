package org.example.springkunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.example.springkunuz.enums.Status;

import java.time.LocalDateTime;
@Setter
@Getter
@Entity
@Table(name = "sms_history")
public class SmsHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "phone")
    private String phone;
    @Column(name = "message")
    private String message;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @Column(name ="created_date")
    private LocalDateTime createdDate=LocalDateTime.now();
}
