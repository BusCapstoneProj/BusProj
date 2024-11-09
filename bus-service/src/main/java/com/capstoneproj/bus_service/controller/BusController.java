package com.capstoneproj.bus_service.controller;

import com.capstoneproj.bus_service.entity.Bus;
import com.capstoneproj.bus_service.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/buses")
public class BusController {

    @Autowired
    private BusService busService;

    // Add a new bus
    @PostMapping
    public ResponseEntity<Bus> addBus(@RequestBody Bus bus) {
        Bus savedBus = busService.addBus(bus);
        return ResponseEntity.ok(savedBus);
    }

    // Delete a bus
    @DeleteMapping("/{busId}")
    public ResponseEntity<Void> deleteBus(@PathVariable String busId) {
        busService.deleteBus(busId);
        return ResponseEntity.noContent().build();
    }

    // Update a bus
    @PutMapping("/{busId}")
    public ResponseEntity<Bus> updateBus(@PathVariable String busId, @RequestBody Bus busDetails) {
        Bus updatedBus = busService.updateBus(busId, busDetails);
        return ResponseEntity.ok(updatedBus);
    }

    // Get current occupancy of a specific bus
    @GetMapping("/{busId}/occupancy")
    public ResponseEntity<Integer> getOccupancy(@PathVariable String busId) {
        int occupancy = busService.getCurrentOccupancy(busId);
        return ResponseEntity.ok(occupancy);
    }

    // Update bus location after each stop
    @PutMapping("/{busId}/location")
    public ResponseEntity<Void> updateLocation(@PathVariable String busId, @RequestParam String newLocation) {
        busService.updateBusLocation(busId, newLocation);
        return ResponseEntity.ok().build();
    }

 /*   // Update occupancy when passengers board/deboard
    @PutMapping("/{busId}/occupancy")
    public ResponseEntity<Void> updateOccupancy(@PathVariable String busId, @RequestParam int delta) {
        busService.updateOccupancy(busId, delta);
        return ResponseEntity.ok().build();
    } */
}
