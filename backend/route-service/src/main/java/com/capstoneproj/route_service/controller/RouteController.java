package com.capstoneproj.route_service.controller;

import com.capstoneproj.route_service.client.BusClient;
import com.capstoneproj.route_service.entity.Route;
import com.capstoneproj.route_service.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/routes")

public class RouteController {

    @Autowired
    private RouteService routeService;

    @Autowired
    private BusClient busClient;

    // Endpoint to get all routes


    // Endpoint to get a route by ID
    @GetMapping("/{routeId}")
    public ResponseEntity<Route> getRouteById(@PathVariable String routeId) {
        Route route = routeService.getRouteById(routeId);
        if (route != null) {
            return new ResponseEntity<>(route, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to add a new route
    @PostMapping
    public ResponseEntity<Route> addRoute(@RequestBody Route route) {
        Route savedRoute = routeService.addRoute(route);
        return new ResponseEntity<>(savedRoute, HttpStatus.CREATED);
    }

    // Endpoint to update an existing route
    @PutMapping("/{routeId}")
    public ResponseEntity<Route> updateRoute(@PathVariable String routeId, @RequestBody Route route) {
        Route updatedRoute = routeService.updateRoute(routeId, route);
        if (updatedRoute != null) {
            return new ResponseEntity<>(updatedRoute, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Endpoint to delete a route
    @DeleteMapping("/deleteRoute/{routeId}")
    public ResponseEntity<Void> deleteRoute(@PathVariable String routeId) {
        boolean isDeleted = routeService.deleteRoute(routeId);
        if (isDeleted) {
             busClient.getBusesByRouteForDeleting(routeId);
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Find route by source and destination
    @GetMapping("/findRoute")
    public ResponseEntity<Route> findRouteByStops(@RequestParam String source, @RequestParam String destination) {
        Optional<Route> route = routeService.findRouteByStops(source, destination);
        return route.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @GetMapping("/allRoutes")
    public ResponseEntity<List<Route>> getAllRoutes()
    {
        return ResponseEntity.ok(routeService.getAllRoute());
    }

}
