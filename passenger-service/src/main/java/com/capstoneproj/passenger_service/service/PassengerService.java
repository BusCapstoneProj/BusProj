package com.capstoneproj.passenger_service.service;

import com.capstoneproj.passenger_service.dto.BusDto;
import com.capstoneproj.passenger_service.dto.RouteDto;
import com.capstoneproj.passenger_service.entity.Passenger;
import com.capstoneproj.passenger_service.repository.PassengerRepository;
import com.capstoneproj.passenger_service.client.RouteClient;
import com.capstoneproj.passenger_service.client.BusClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PassengerService {

    @Autowired
    private PassengerRepository passengerRepository;

    @Autowired
    private RouteClient routeClient;

    @Autowired
    private BusClient busClient;

    public Passenger createPassenger(Passenger passenger)
    {
        return passengerRepository.save(passenger);
    }

    public RouteDto findRouteByStops(String source, String destination) {
        return routeClient.findRouteByStops(source, destination);
    }

    public List<BusDto> getBusesByRoute(String routeId) {
        return busClient.getBusesByRoute(routeId);
    }

    @Transactional
    public Passenger boardBus(String passengerId, String busId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        passenger.setSelectedBusId(busId);
        passengerRepository.save(passenger);

        try {
            busClient.updateOccupancy(busId, -1);  // Decrease occupancy by 1
        } catch (Exception e) {
            throw new RuntimeException("Failed to update bus occupancy: " + e.getMessage());
        }

        return passenger;
    }

    @Transactional
    public Passenger deboardBus(String passengerId) {
        Passenger passenger = passengerRepository.findById(passengerId)
                .orElseThrow(() -> new RuntimeException("Passenger not found"));

        String busId = passenger.getSelectedBusId();
        if (busId != null) {
            try {
                busClient.updateOccupancy(busId, 1);  // Increase occupancy by 1
                passenger.setSelectedBusId(null);
                passengerRepository.save(passenger);
            } catch (Exception e) {
                throw new RuntimeException("Failed to update bus occupancy: " + e.getMessage());
            }
        }

        return passenger;
    }
}
