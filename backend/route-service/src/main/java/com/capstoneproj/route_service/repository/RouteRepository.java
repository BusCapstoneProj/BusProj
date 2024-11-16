package com.capstoneproj.route_service.repository;

import com.capstoneproj.route_service.entity.Route;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteRepository extends MongoRepository<Route,String> {
}
