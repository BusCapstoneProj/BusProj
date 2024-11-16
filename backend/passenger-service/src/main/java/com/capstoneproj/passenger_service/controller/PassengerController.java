package com.capstoneproj.passenger_service.controller;

import com.capstoneproj.passenger_service.dto.BusDto;
import com.capstoneproj.passenger_service.dto.RouteDto;
import com.capstoneproj.passenger_service.entity.Passenger;
import com.capstoneproj.passenger_service.service.PassengerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/passengers")
public class PassengerController {

    @Autowired
    private PassengerService passengerService;

    @PostMapping
    public ResponseEntity<Passenger> createPassenger(@RequestBody Passenger passenger)
    {
        passengerService.createPassenger(passenger);
        return ResponseEntity.ok(passengerService.createPassenger(passenger));
    }

    @GetMapping("/findRoute")
    public ResponseEntity<RouteDto> findRouteByStops(
            @RequestParam String source,
            @RequestParam String destination) {
        RouteDto route = passengerService.findRouteByStops(source, destination);
        if (route != null) {
            return ResponseEntity.ok(route);
        }
        return ResponseEntity.notFound().build();
    }

    // Endpoint to get all buses for a specific route
    @GetMapping("/route/{routeId}/buses")
    public ResponseEntity<List<BusDto>> getBusesByRoute(@PathVariable String routeId) {
        List<BusDto> buses = passengerService.getBusesByRoute(routeId);
        return ResponseEntity.ok(buses);
    }

    @PutMapping("/{passengerId}/board")
    public ResponseEntity<Passenger> boardBus(
            @PathVariable String passengerId,
            @RequestParam String busId) {
        Passenger passenger = passengerService.boardBus(passengerId, busId);
        return ResponseEntity.ok(passenger);
    }

    @PutMapping("/{passengerId}/deboard")
    public ResponseEntity<Passenger> deboardBus(@PathVariable String passengerId) {
        Passenger passenger = passengerService.deboardBus(passengerId);
        return ResponseEntity.ok(passenger);
    }
}
