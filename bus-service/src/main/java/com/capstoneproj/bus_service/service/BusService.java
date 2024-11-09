package com.capstoneproj.bus_service.service;

import com.capstoneproj.bus_service.entity.Bus;
import com.capstoneproj.bus_service.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;



    // Add a new bus
    public Bus addBus(Bus bus) {
        bus.setCurrentOccupancy(0);  // Initialize occupancy to zero
        return busRepository.save(bus);
    }

    // Delete a bus
    public void deleteBus(String busId) {
        if (!busRepository.existsById(busId)) {
            throw new RuntimeException("Bus not found");
        }
        busRepository.deleteById(busId);
    }

    // Update a bus
    public Bus updateBus(String busId, Bus busDetails) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        bus.setSeatCapacity(busDetails.getSeatCapacity());
        bus.setCurrentLocation(busDetails.getCurrentLocation());
        return busRepository.save(bus);
    }

    // Fetch current occupancy of a specific bus
    public int getCurrentOccupancy(String busId) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        return bus.getCurrentOccupancy();
    }

    // Update bus location after each stop
    public void updateBusLocation(String busId, String newLocation) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        bus.setCurrentLocation(newLocation);
        busRepository.save(bus);

        // Notify Admin service of location update
        //adminClient.notifyLocationUpdate(busId, newLocation);
    }

   /* // Update occupancy when passengers board/deboard
    public void updateOccupancy(String busId, int delta) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        int newOccupancy = bus.getCurrentOccupancy() + delta;

        // Ensure occupancy does not exceed capacity or fall below zero
        if (newOccupancy >= 0 && newOccupancy <= bus.getSeatCapacity()) {
            bus.setCurrentOccupancy(newOccupancy);
            busRepository.save(bus);

            // Notify Admin service about occupancy update
            adminClient.notifyOccupancyUpdate(busId, newOccupancy);
        } else {
            throw new RuntimeException("Invalid occupancy update");
        }
    } */
}
