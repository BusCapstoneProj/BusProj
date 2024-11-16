package com.capstoneproj.admin_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document

public class Admin {
    @Id
    private String adminId;
    private String adminName;
    private String adminEmail;
    private String adminPhoneNumber;

    // Getters and setters
}


