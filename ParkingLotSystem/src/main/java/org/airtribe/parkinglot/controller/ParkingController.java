package org.airtribe.parkinglot.controller;

import org.airtribe.parkinglot.entity.ParkingSpot;
import org.airtribe.parkinglot.entity.ParkingTransaction;
import org.airtribe.parkinglot.entity.Vehicle;
import org.airtribe.parkinglot.repository.ParkingSpotRepository;
import org.airtribe.parkinglot.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {

    @Autowired
    private ParkingService parkingService;
    @Autowired private ParkingSpotRepository spotRepo;

    @PostMapping("/checkin")
    public ResponseEntity<ParkingTransaction> checkIn(@RequestBody Vehicle vehicle) {
        ParkingTransaction tx = parkingService.checkIn(vehicle);
        return ResponseEntity.ok(tx);
    }

    @PostMapping("/checkout/{transactionId}")
    public ResponseEntity<ParkingTransaction> checkOut(@PathVariable Long transactionId) {
        ParkingTransaction tx = parkingService.checkOut(transactionId);
        return ResponseEntity.ok(tx);
    }

    @GetMapping("/spots/available")
    public ResponseEntity<List<ParkingSpot>> getAvailableSpots() {
        List<ParkingSpot> spots = spotRepo.findAll()
                .stream()
                .filter(s -> !s.isOccupied())
                .toList();
        return ResponseEntity.ok(spots);
    }
}
