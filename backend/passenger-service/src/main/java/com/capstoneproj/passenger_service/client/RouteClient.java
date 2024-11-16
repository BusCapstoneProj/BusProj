package com.capstoneproj.passenger_service.client;

import com.capstoneproj.passenger_service.dto.RouteDto;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "route-service", url = "http://localhost:8083/api/routes")
public interface RouteClient {

    @GetMapping("/findRoute")
    RouteDto findRouteByStops(@RequestParam String source, @RequestParam String destination);
}

