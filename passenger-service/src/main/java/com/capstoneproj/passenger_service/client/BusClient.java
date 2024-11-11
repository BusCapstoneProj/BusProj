package com.capstoneproj.passenger_service.client;

import com.capstoneproj.passenger_service.dto.BusDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;



@FeignClient(name = "bus-service", url = "http://localhost:8082/api/buses")
public interface BusClient {

    @GetMapping("/route/{routeId}")
    List<BusDto> getBusesByRoute(@PathVariable String routeId);

    @PutMapping("/{busId}/occupancy")
    void updateOccupancy(@PathVariable String busId, @RequestParam int delta);
}



