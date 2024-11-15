package com.capstoneproj.route_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bus-service",url = "http://localhost:8082/api/buses")
public interface BusClient {
    @DeleteMapping("/deleteRoute/{routeId}")
    void  getBusesByRouteForDeleting(@PathVariable String routeId);


}

