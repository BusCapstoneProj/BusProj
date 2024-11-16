package com.capstoneproj.admin_service.service;

import com.capstoneproj.admin_service.client.BusClient;
import com.capstoneproj.admin_service.client.RouteClient;
import com.capstoneproj.admin_service.dto.BusDto;
import com.capstoneproj.admin_service.dto.RouteDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

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
<<<<<<< HEAD
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



=======
    public String assignRouteToBus(String busId, String routeId) {
        return busClient.updateBusByRoute(busId, routeId);
    }

//    public String notifyAdminToAddBus(String busId, String routeId);
//    {
//        System.out.println("Notification received for low occupancy on route: " + routeId);
//
//        // Trigger the logic to add a bus when notified
//        return busClient.addBusWhenNotified(busId,routeId);
//
//
//    }
public String notifyAdminToAddBus(String routeId){
        String busId = busClient.getIdleBus();
        busClient.updateBusByRoute(busId,routeId);
        return "Bus added to route";
}

    // Add or update a route in the RouteService
    /* public Route saveOrUpdateRoute(Route route) {
        if (route.getRouteId() != null) {
            return routeClient.updateRoute(route.getRouteId(), route);
        } else {
            return routeClient.addRoute(route);
        }
    } */
>>>>>>> refs/remotes/origin/master
}

