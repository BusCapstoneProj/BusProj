package com.capstoneproj.admin_service.controller;
import com.capstoneproj.admin_service.client.RouteClient;
import com.capstoneproj.admin_service.dto.BusDto;
import com.capstoneproj.admin_service.dto.RouteDto;
import com.capstoneproj.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RouteClient routeClient;

    // Add a new bus
    @PostMapping("/buses")
    public ResponseEntity<BusDto> addBus(@RequestBody BusDto bus) {
        BusDto addedBus = adminService.addBus(bus);
        return ResponseEntity.ok(addedBus);
    }

    // Delete a bus
    @DeleteMapping("/buses/{busId}")
    public ResponseEntity<Void> deleteBus(@PathVariable String busId) {
        adminService.deleteBus(busId);
        return ResponseEntity.noContent().build();
    }

    // Update a bus
    @PutMapping("/buses/{busId}")
    public ResponseEntity<BusDto> updateBus(@PathVariable String busId, @RequestBody BusDto busDetails) {
        BusDto updatedBus = adminService.updateBus(busId, busDetails);
        return ResponseEntity.ok(updatedBus);
    }

    // Assign a route to a bus
    @PutMapping("/buses/route")
    public ResponseEntity<Void> assignRouteToBus(@RequestParam String busId, @RequestParam String routeId) {
        adminService.assignRouteToBus(busId, routeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/allBuses")
    public ResponseEntity<List<BusDto>> getAllBuses()
    {
        return ResponseEntity.ok(adminService.getAllBuses());
    }

    // Save or update a route
    /*@PutMapping("/routes")
    public ResponseEntity<Route> saveOrUpdateRoute(@RequestBody Route route) {
        Route savedRoute = adminService.saveOrUpdateRoute(route);
        return ResponseEntity.ok(savedRoute);
    }  */
    @PostMapping("/routes")
    public ResponseEntity<RouteDto> addRoute(@RequestBody RouteDto route) {
        RouteDto addedRoute = adminService.addRoute(route);
        return ResponseEntity.ok(addedRoute);
    }

    // Delete a route
    @DeleteMapping("/routes/{routeId}")
    public ResponseEntity<Void> deleteRoute(@PathVariable String routeId) {
        adminService.deleteRoute(routeId);
        return ResponseEntity.noContent().build();
    }

    // Update a route
    @PutMapping("/routes/{routeId}")
    public ResponseEntity<RouteDto> updateRoute(@PathVariable String routeId, @RequestBody RouteDto routeDetails) {
        RouteDto updatedRoute = adminService.updateRoute(routeId, routeDetails);
        return ResponseEntity.ok(updatedRoute);
    }


}
