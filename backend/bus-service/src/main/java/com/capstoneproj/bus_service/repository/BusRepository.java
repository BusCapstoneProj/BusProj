package com.capstoneproj.bus_service.repository;

import com.capstoneproj.bus_service.entity.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BusRepository extends MongoRepository<Bus,String> {
    List<Bus> findByRouteIdIsNull();
}
