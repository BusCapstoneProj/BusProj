package com.capstoneproj.bus_service.service;

import com.capstoneproj.bus_service.entity.Bus;
import com.capstoneproj.bus_service.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    private static final int LOW_OCCUPANCY_THRESHOLD = 10;  // Threshold for adding a bus
    private static final int HIGH_OCCUPANCY_THRESHOLD = 30; // Threshold for deleting a bus
    private static final int MAX_CONSECUTIVE_STOPS = 3;     // Number of consecutive stops for threshold check

    private int consecutiveLowOccupancyStops = 0;


    // Add a new bus
    public Bus addBus(Bus bus) {
        bus.setCurrentOccupancy(bus.getSeatCapacity());  // Initialize occupancy to zero
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
    /*

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

    public List<Bus> getBusesByRouteId(String routeId) {
        return busRepository.findAll().stream()
                .filter(bus -> routeId.equals(bus.getRouteId()))
                .collect(Collectors.toList());
    }

    public void updateOccupancy(String busId, int delta) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        int newOccupancy = bus.getCurrentOccupancy() + delta;
        if (newOccupancy >= 0 && newOccupancy <= bus.getSeatCapacity()) {
            bus.setCurrentOccupancy(newOccupancy);
            busRepository.save(bus);
            checkThresholdAndUpdateBusCount(bus, newOccupancy);
        } else {
            throw new RuntimeException("Invalid occupancy update");
        }
    }

    private void checkThresholdAndUpdateBusCount(Bus bus, int newOccupancy) {
        // Track stops for occupancy below threshold
        if (newOccupancy < LOW_OCCUPANCY_THRESHOLD) {
            consecutiveLowOccupancyStops++;
            if (consecutiveLowOccupancyStops >= MAX_CONSECUTIVE_STOPS) {
                addNewBusIfNeeded(bus);
                consecutiveLowOccupancyStops = 0;
            }
        } else {
            consecutiveLowOccupancyStops = 0;
        }

        // Check for high occupancy threshold to delete a bus
        if (newOccupancy > HIGH_OCCUPANCY_THRESHOLD) {
            deleteBusIfNeeded(bus);
        }
    }
        private void addNewBusIfNeeded(Bus bus) {
            Bus newBus = new Bus();
            newBus.setSeatCapacity(bus.getSeatCapacity());
            newBus.setRouteId(bus.getRouteId());
            newBus.setCurrentLocation(bus.getCurrentLocation());
            busRepository.save(newBus);
            System.out.println("Added new bus due to low occupancy threshold met for consecutive stops.");
        }


    // Service method to handle occupancy checks and conditional bus adjustments
    public void updateStopAndCheckThreshold(String busId, String location, int deltaOccupancy) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        // Update location and occupancy
        bus.setCurrentLocation(location);
        updateOccupancy(busId, deltaOccupancy);

        // Check threshold conditions for adding or deleting a bus
        if (bus.getCurrentOccupancy() < LOW_OCCUPANCY_THRESHOLD) {
            consecutiveLowOccupancyStops++;
            if (consecutiveLowOccupancyStops >= 3) {
                addBus(new Bus());  // Add new bus logic
                consecutiveLowOccupancyStops = 0; // Reset counter
            }
        } else if (bus.getCurrentOccupancy() > HIGH_OCCUPANCY_THRESHOLD) {
            consecutiveLowOccupancyStops = 0; // Reset counter if occupancy is above low threshold
            deleteBus(busId);  // Remove bus logic
        }

        // Save changes to the bus
        busRepository.save(bus);
    }


    // Delete bus if occupancy is consistently high
        private void deleteBusIfNeeded(Bus bus) {
            List<Bus> busesOnRoute = getBusesByRouteId(bus.getRouteId());
            if (busesOnRoute.size() > 1) { // Ensure at least one bus remains on the route
                busRepository.deleteById(bus.getBusId());
                System.out.println("Deleted a bus due to high occupancy threshold exceeded.");
            }
        }

        public void busBoard(String busId)
        {
            Optional<Bus> bus=busRepository.findById(busId);
            if(bus.isPresent())
            {
                bus.get().setCurrentOccupancy((bus.get().getCurrentOccupancy())-1);
                busRepository.save(bus.get());
            }

        }

    public void busDeBoard(String busId)
    {
        Optional<Bus> bus=busRepository.findById(busId);
        if(bus.isPresent())
        {
            bus.get().setCurrentOccupancy((bus.get().getCurrentOccupancy())+ 1);
            busRepository.save(bus.get());
        }

    }


    public void updateBusByRoute(String busId, String routeId) {

        // Fetch the bus by busId

        Bus bus = busRepository.findById(busId)

                .orElseThrow(() -> new RuntimeException("Bus not found"));

        // Update the routeId

        bus.setRouteId(routeId);

        // Save the updated bus back to the repository

        busRepository.save(bus);

    }

    //Get all Buses
    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }
}
