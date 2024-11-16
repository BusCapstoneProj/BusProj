package com.capstoneproj.route_service.service;

import com.capstoneproj.route_service.entity.Route;
import com.capstoneproj.route_service.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RouteService {

    @Autowired
    private RouteRepository routeRepository;

    // Method to add a new route
    public Route addRoute(Route route) {
        return routeRepository.save(route);
    }

    // Method to get a list of all routes
    public List<Route> getAllRoutes() {
        return routeRepository.findAll();
    }

    // Method to get a route by its ID
    public Route getRouteById(String routeId) {
        Optional<Route> route = routeRepository.findById(routeId);
        return route.orElse(null);  // Returns null if not found
    }

    // Method to update a route
    public Route updateRoute(String routeId, Route updatedRoute) {
        Optional<Route> optionalRoute = routeRepository.findById(routeId);
        if (optionalRoute.isPresent()) {
            Route existingRoute = optionalRoute.get();
            existingRoute.setRouteSource(updatedRoute.getRouteSource());
            existingRoute.setRouteDestination(updatedRoute.getRouteDestination());
            existingRoute.setRouteStops(updatedRoute.getRouteStops());
            return routeRepository.save(existingRoute);
        }
        return null;  // If route not found, return null
    }

    // Method to delete a route
    public boolean deleteRoute(String routeId) {
        Optional<Route> route = routeRepository.findById(routeId);
        if (route.isPresent()) {
            routeRepository.delete(route.get());
            return true;
        }
        return false;  // Return false if route not found
    }

    // Find route by source and destination with correct stop order
    public Optional<Route> findRouteByStops(String source, String destination) {
        return routeRepository.findAll().stream()
                .filter(route -> {
                    List<String> stops = route.getRouteStops();
                    int sourceIndex = stops.indexOf(source);
                    int destinationIndex = stops.indexOf(destination);
                    return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
                })
                .findFirst();  // Return the first route that matches the criteria, if any
    }


    public List<Route>  getAllRoute()
    {
        return routeRepository.findAll();
    }
}
