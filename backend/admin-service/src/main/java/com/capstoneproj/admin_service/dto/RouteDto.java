package com.capstoneproj.admin_service.dto;
import java.util.List;


    public class RouteDto {

        private String routeId;
        private String routeSource;
        private String routeDestination;
        private List<String> routeStops;

        // Default constructor
        public RouteDto() {}

        // Constructor to initialize all fields
        public RouteDto(String routeId, String routeSource, String routeDestination, List<String> routeStops) {
            this.routeId = routeId;
            this.routeSource = routeSource;
            this.routeDestination = routeDestination;
            this.routeStops = routeStops;
        }

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





