package com.capstoneproj.bus_service.controller;

import com.capstoneproj.bus_service.entity.Bus;
import com.capstoneproj.bus_service.repository.BusRepository;
import com.capstoneproj.bus_service.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/buses")
@CrossOrigin(origins = "http://localhost:5173")
public class BusController {

    @Autowired
    private BusService busService;

    @Autowired
    private BusRepository busRepository;

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

    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<Bus>> getBusesByRoute(@PathVariable String routeId) {
        List<Bus> buses = busService.getBusesByRouteId(routeId);
        return ResponseEntity.ok(buses);
    }

    @GetMapping("/allBuses")
    public ResponseEntity<List<Bus>> getAllBuses()
    {
        return ResponseEntity.ok(busService.getAllBuses());
    }

    /*// Get current occupancy of a specific bus
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
    } */
  /*  @PutMapping("/{busId}/occupancy")
    public ResponseEntity<Void> updateOccupancy(
            @PathVariable String busId,
            @RequestParam int delta) {
        busService.updateOccupancy(busId, delta);
        return ResponseEntity.ok().build();
    } */

    // Controller to handle stop updates and occupancy-based decisions
    @PutMapping("/{busId}/stop")
    public ResponseEntity<Void> updateStop(
            @PathVariable String busId,
            @RequestParam String location,
            @RequestParam int deltaOccupancy) {
        busService.updateStopAndCheckThreshold(busId, location, deltaOccupancy);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{busId}/board")
    public ResponseEntity<Void> busBoard(@PathVariable String busId){
         busService.busBoard(busId) ;
    return ResponseEntity.noContent().build();
    }

    @PutMapping("/{busId}/deboard")
    public ResponseEntity<Void> busDeBoard(@PathVariable String busId){
        busService.busBoard(busId) ;
        return ResponseEntity.noContent().build();
    }

    //update bus by route

    @PutMapping("/updateRoute/{busId}/{routeId}")

    public String updateBusByRoute(@PathVariable String busId, @PathVariable String routeId) {

        busService.updateBusByRoute(busId, routeId);

        return "updated";

    }


    @DeleteMapping("/deleteRoute/{routeId}")
    public void  getBusesByRouteForDeleting(@PathVariable String routeId) {
        List<Bus> buses = busService.getBusesByRouteId(routeId);
        for(Bus bus:buses)
        {
            bus.setRouteId("null");
            busRepository.save(bus);
        }


    }



    @PutMapping("/setRouteToBus/{busId}/{routeId}")
    public void setRouteToBus(@PathVariable String busId,@PathVariable String routeId)
    {
        Optional<Bus> bus=busRepository.findById(busId);
        if(bus.isPresent())
        {
            bus.get().setRouteId(routeId);
            busRepository.save(bus.get());
        }
    }

}
