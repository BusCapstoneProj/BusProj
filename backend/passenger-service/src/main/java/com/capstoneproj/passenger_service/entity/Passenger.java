package com.capstoneproj.passenger_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document

public class Passenger {
    @Id
    private String passengerId;
    private String name;
    private String source;
    private String destination;
    private String selectedBusId;
}

