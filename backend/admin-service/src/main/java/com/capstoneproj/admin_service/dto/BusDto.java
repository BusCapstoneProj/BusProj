package com.capstoneproj.admin_service.dto;

public class BusDto{

    private String busId;
    private int seatCapacity;
    private int currentOccupancy;
    private String currentLocation;
    private String routeId;
    private String adminId;

    // Default constructor
    public BusDto() {
    }

    // Parameterized constructor
    public BusDto(String busId, int seatCapacity, int currentOccupancy, String currentLocation, String routeId, String adminId) {
        this.busId = busId;
        this.seatCapacity = seatCapacity;
        this.currentOccupancy = currentOccupancy;
        this.currentLocation = currentLocation;
        this.routeId = routeId;
        //this.adminId = adminId;
    }

    // Getters and Setters
    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public int getCurrentOccupancy() {
        return currentOccupancy;
    }

    public void setCurrentOccupancy(int currentOccupancy) {
        this.currentOccupancy = currentOccupancy;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
}
