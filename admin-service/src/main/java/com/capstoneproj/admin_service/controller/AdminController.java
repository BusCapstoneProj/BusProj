package com.capstoneproj.admin_service.controller;
import com.capstoneproj.admin_service.dto.BusDto;
import com.capstoneproj.admin_service.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    // Add a new bus
    @PostMapping("/bus")
    public ResponseEntity<BusDto> addBus(@RequestBody BusDto bus) {
        BusDto addedBus = adminService.addBus(bus);
        return ResponseEntity.ok(addedBus);
    }

    // Delete a bus
    @DeleteMapping("/bus/{busId}")
    public ResponseEntity<Void> deleteBus(@PathVariable String busId) {
        adminService.deleteBus(busId);
        return ResponseEntity.noContent().build();
    }

    // Update a bus
    @PutMapping("/bus/{busId}")
    public ResponseEntity<BusDto> updateBus(@PathVariable String busId, @RequestBody BusDto busDetails) {
        BusDto updatedBus = adminService.updateBus(busId, busDetails);
        return ResponseEntity.ok(updatedBus);
    }

    // Assign a route to a bus
    @PutMapping("/bus/{busId}/route")
    public ResponseEntity<String> assignRouteToBus(@PathVariable String busId, @RequestParam String routeId) {

        return new ResponseEntity<>(adminService.assignRouteToBus(busId, routeId), HttpStatus.OK);
    }

    @PostMapping("/notify")
    public ResponseEntity<?> notifyAdminToAddBus(@RequestParam String routeId)
    {
        return new ResponseEntity<>(adminService.notifyAdminToAddBus(routeId),HttpStatus.OK);

}

    // Save or update a route
    /*@PutMapping("/routes")
    public ResponseEntity<Route> saveOrUpdateRoute(@RequestBody Route route) {
        Route savedRoute = adminService.saveOrUpdateRoute(route);
        return ResponseEntity.ok(savedRoute);
    }  */
}
