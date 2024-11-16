package com.capstoneproj.passenger_service.repository;

import com.capstoneproj.passenger_service.entity.Passenger;

import org.springframework.data.mongodb.repository.MongoRepository;


public interface PassengerRepository extends MongoRepository<Passenger, String> {

}


