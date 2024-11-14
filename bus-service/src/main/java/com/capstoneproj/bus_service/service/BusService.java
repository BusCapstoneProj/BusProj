package com.capstoneproj.bus_service.service;

import com.capstoneproj.bus_service.entity.Bus;
import com.capstoneproj.bus_service.repository.BusRepository;
import com.capstoneproj.bus_service.client.AdminClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private AdminClient adminClient;

    // Existing method: Add a new bus
    public Bus addBus(Bus bus) {
        return busRepository.save(bus);
    }

    // Existing method: Update bus details
    public Bus updateBus(String busId, Bus busDetails) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
//        bus.setSeatCapacity(busDetails.getSeatCapacity());
        bus.setCurrentLocation(busDetails.getCurrentLocation());
        updateOccupancy(busId,0);
        return busRepository.save(bus);

    }

    // Existing method: Delete a bus
    public void deleteBus(String busId) {
        if (!busRepository.existsById(busId)) {
            throw new RuntimeException("Bus not found");
        }
        busRepository.deleteById(busId);
    }


    // Existing method: Get buses by routeId
    public List<Bus> getBusesByRouteId(String routeId) {
        return busRepository.findAll().stream()
                .filter(bus -> routeId.equals(bus.getRouteId()))
                .collect(Collectors.toList());
    }

    // Existing method: Update bus occupancy
    public void updateOccupancy(String busId, int delta) {
        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));



        // Update the current occupancy
        int newOccupancy = bus.getCurrentOccupancy() + delta;
        if (newOccupancy >= 0 && newOccupancy <= bus.getSeatCapacity()) {
            bus.setCurrentOccupancy(newOccupancy);
            busRepository.save(bus);
            List<Integer> listo = bus.getOccupancyHistory();
          // Add the new occupancy to history
            if(newOccupancy <=10 || newOccupancy ==0) {
                listo.add(newOccupancy);
            }
            else if(listo != null){

                listo.clear();
            }
            bus.setOccupancyHistory(listo);

         // Check for low occupancy and notify admin if necessary
           if (shouldNotifyAdmin(bus)) {
                notifyAdminToAddBus(bus);
            }
       } else {
          throw new RuntimeException("Invalid occupancy update");
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

    // New method: Set occupancy history (track the last 3 occupancy records)
//    private void setOccupancyHistory(Bus bus) {
//        if (bus.getOccupancyHistory() == null) {
//            bus.setOccupancyHistory(new ArrayList<>());
//        }
//        bus.getOccupancyHistory().add(bus.getCurrentOccupancy());
//        if (bus.getOccupancyHistory().size() > 3) {
//            bus.getOccupancyHistory().remove(0);  // Keep only the last 3 occupancy records
//        }
//    }

    // New method: Get the occupancy history (returns the last 3 occupancy records)
//    private List<Integer> getOccupancyHistory(Bus bus) {
//        return bus.getOccupancyHistory();
//    }

    // New method: Check if the bus has low occupancy for 3 consecutive stops
    private boolean shouldNotifyAdmin(Bus bus) {
        List<Integer> occupancyHistory = bus.getOccupancyHistory();
        return occupancyHistory.size() == 3 && occupancyHistory.stream().allMatch(o -> o <= 10);
    }

    // New method: Notify admin to add a new bus if occupancy is low
    private void notifyAdminToAddBus(Bus bus) {
        adminClient.notifyAdminToAddBus(bus.getRouteId());
    }
}