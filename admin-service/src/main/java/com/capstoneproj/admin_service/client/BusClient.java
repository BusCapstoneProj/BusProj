package com.capstoneproj.admin_service.client;


import com.capstoneproj.admin_service.dto.BusDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "bus-service",url = "http://localhost:8082/api/buses")
public interface BusClient {

    @PostMapping
    BusDto addBus(@RequestBody BusDto bus);

    @DeleteMapping("/{busId}")
    void deleteBus(@PathVariable String busId);

    @PutMapping("/{busId}")
    BusDto updateBus(@PathVariable String busId, @RequestBody BusDto bus);

    @GetMapping("/allBuses")
    public List<BusDto> getAllBuses();



    @PutMapping("/updateRoute/{busId}/{routeId}")
    void updateBusByRoute(@PathVariable String busId, @PathVariable String routeId);

     /* @GetMapping("/bus/{busId}/occupancy")
    int getCurrentOccupancy(@PathVariable String busId); */
}


