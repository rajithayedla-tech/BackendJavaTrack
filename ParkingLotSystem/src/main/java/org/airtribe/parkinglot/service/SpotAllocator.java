package org.airtribe.parkinglot.service;

import org.airtribe.parkinglot.entity.ParkingSpot;
import org.airtribe.parkinglot.entity.VehicleType;
import org.airtribe.parkinglot.repository.ParkingSpotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpotAllocator {

    @Autowired private ParkingSpotRepository spotRepo;

    /**
     * Allocates the first available spot for the given vehicle type.
     * Uses JPA query method to find the nearest free spot.
     */
    public ParkingSpot allocateSpot(VehicleType type) {
        ParkingSpot spot = spotRepo
                .findFirstBySizeAndIsOccupiedFalseOrderByFloorAscSpotNumberAsc(type)
                .orElseThrow(() -> new RuntimeException("No available spot for type: " + type));

        spot.setOccupied(true); // mark spot as reserved
        return spot;
    }
}

