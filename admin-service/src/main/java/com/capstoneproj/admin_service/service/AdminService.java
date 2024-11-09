package com.capstoneproj.admin_service.service;

import com.capstoneproj.admin_service.client.BusClient;
import com.capstoneproj.admin_service.dto.BusDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private BusClient busClient;



    // Add a new bus
    public BusDto addBus(BusDto bus) {
        return busClient.addBus(bus);
    }

    // Delete a bus
    public void deleteBus(String busId) {
        busClient.deleteBus(busId);
    }

    // Update a bus
    public BusDto updateBus(String busId, BusDto busDetails) {
        return busClient.updateBus(busId, busDetails);
    }

    // Assign a route to a bus
    public void assignRouteToBus(String busId, String routeId) {
        busClient.assignRouteToBus(busId, routeId);
    }

    // Add or update a route in the RouteService
    /* public Route saveOrUpdateRoute(Route route) {
        if (route.getRouteId() != null) {
            return routeClient.updateRoute(route.getRouteId(), route);
        } else {
            return routeClient.addRoute(route);
        }
    } */
}

