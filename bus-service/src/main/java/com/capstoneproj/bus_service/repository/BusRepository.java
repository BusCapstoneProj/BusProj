package com.capstoneproj.bus_service.repository;

import com.capstoneproj.bus_service.entity.Bus;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BusRepository extends MongoRepository<Bus,String> {
}
