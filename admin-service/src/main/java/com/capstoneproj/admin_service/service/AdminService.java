package com.capstoneproj.admin_service.service;

import com.capstoneproj.admin_service.client.BusClient;
import com.capstoneproj.admin_service.client.RouteClient;
import com.capstoneproj.admin_service.dto.BusDto;
import com.capstoneproj.admin_service.dto.RouteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private BusClient busClient;

    @Autowired
    private RouteClient routeClient;



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
        busClient.updateBusByRoute(busId, routeId);
    }

    public List<BusDto> getAllBuses()
    {
       return  busClient.getAllBuses();
    }



    public RouteDto addRoute(RouteDto route) {
        return routeClient.addRoute(route);
    }

    // Delete a bus
    public void deleteRoute(String routeId) { routeClient.deleteRoute(routeId);
    }

    // Update a bus
    public RouteDto updateRoute(String routeId, RouteDto routeDetails) {
        return routeClient.updateRoute(routeId, routeDetails);
    }



}

