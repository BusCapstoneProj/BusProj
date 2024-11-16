package com.capstoneproj.route_service.entity;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document()
@Data
public class Route {

    @Id
    private String routeId;
    private String routeSource;
    private String routeDestination;
    private List<String> routeStops;

    // Getters and Setters
    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteSource() {
        return routeSource;
    }

    public void setRouteSource(String routeSource) {
        this.routeSource = routeSource;
    }

    public String getRouteDestination() {
        return routeDestination;
    }

    public void setRouteDestination(String routeDestination) {
        this.routeDestination = routeDestination;
    }

    public List<String> getRouteStops() {
        return routeStops;
    }

    public void setRouteStops(List<String> routeStops) {
        this.routeStops = routeStops;
    }
}

