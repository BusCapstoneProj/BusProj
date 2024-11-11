package com.capstoneproj.route_service.controller;

import com.capstoneproj.route_service.entity.Route;
import com.capstoneproj.route_service.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    @Autowired
    private RouteService routeService;

    // Endpoint to get all routes
    @GetMapping
    public List<Route> getAllRoutes() {
        return routeService.getAllRoutes();
    }

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
    @DeleteMapping("/{routeId}")
    public ResponseEntity<Void> deleteRoute(@PathVariable String routeId) {
        boolean isDeleted = routeService.deleteRoute(routeId);
        if (isDeleted) {
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

}
