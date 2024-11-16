package com.capstoneproj.admin_service.client;


import com.capstoneproj.admin_service.dto.RouteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "route-service",url = "http://localhost:8083/api/routes")
public interface RouteClient {

    @PostMapping
    RouteDto addRoute(@RequestBody RouteDto route);

    @DeleteMapping("/{routeId}")
    void deleteRoute(@PathVariable String routeId);

    @PutMapping("/{routeId}")
    RouteDto updateRoute(@PathVariable String routeId, @RequestBody RouteDto route);



}



