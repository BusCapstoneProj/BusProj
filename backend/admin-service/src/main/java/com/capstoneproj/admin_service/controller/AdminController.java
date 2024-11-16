package com.capstoneproj.admin_service.controller;
import com.capstoneproj.admin_service.dto.BusDto;
import com.capstoneproj.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

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
    @PutMapping("/buses/{busId}/route")
    public ResponseEntity<Void> assignRouteToBus(@PathVariable String busId, @RequestParam String routeId) {
        adminService.assignRouteToBus(busId, routeId);
        return ResponseEntity.ok().build();
    }

    // Save or update a route
    /*@PutMapping("/routes")
    public ResponseEntity<Route> saveOrUpdateRoute(@RequestBody Route route) {
        Route savedRoute = adminService.saveOrUpdateRoute(route);
        return ResponseEntity.ok(savedRoute);
    }  */
}
