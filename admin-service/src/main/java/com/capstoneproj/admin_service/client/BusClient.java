package com.capstoneproj.admin_service.client;


import com.capstoneproj.admin_service.dto.BusDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "bus-service",url = "http://localhost:8082/api/buses")
public interface BusClient {

    @PostMapping
    BusDto addBus(@RequestBody BusDto bus);

    @DeleteMapping("/{busId}")
    void deleteBus(@PathVariable String busId);

    @PutMapping("/{busId}")
    BusDto updateBus(@PathVariable String busId, @RequestBody BusDto bus);

    @PutMapping("/{busId}/assignRoute")
    void assignRouteToBus(@PathVariable String busId, @RequestParam String routeId);

     /* @GetMapping("/bus/{busId}/occupancy")
    int getCurrentOccupancy(@PathVariable String busId); */
}


