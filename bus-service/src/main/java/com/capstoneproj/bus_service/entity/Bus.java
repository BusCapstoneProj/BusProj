package com.capstoneproj.bus_service.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Bus {
    @Id
    private String busId;
    private int seatCapacity;
    private int currentOccupancy;
    private String currentLocation;
    private String routeId;
    private String adminId;


    // New field to track occupancy history over stops
    private List<Integer> occupancyHistory = new ArrayList<>();// Stores occupancy for previous stops
}




