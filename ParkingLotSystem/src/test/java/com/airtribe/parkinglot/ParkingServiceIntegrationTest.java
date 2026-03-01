package com.airtribe.parkinglot;

import org.airtribe.parkinglot.entity.ParkingTransaction;
import org.airtribe.parkinglot.entity.Vehicle;
import org.airtribe.parkinglot.entity.VehicleType;
import org.airtribe.parkinglot.repository.ParkingSpotRepository;
import org.airtribe.parkinglot.repository.ParkingTransactionRepository;
import org.airtribe.parkinglot.repository.VehicleRepository;
import org.airtribe.parkinglot.service.ParkingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ParkingServiceIntegrationTest {

    @Autowired private ParkingService parkingService;
    @Autowired private VehicleRepository vehicleRepo;
    @Autowired private ParkingSpotRepository spotRepo;
    @Autowired private ParkingTransactionRepository transactionRepo;

    @Test
    void testCheckInAndCheckOutFlow() {
        // Step 1: Create a vehicle
        Vehicle vehicle = new Vehicle();
        vehicle.setLicensePlate("TS09AB1234");
        vehicle.setType(VehicleType.CAR);

        // Step 2: Check-in
        ParkingTransaction tx = parkingService.checkIn(vehicle);
        assertNotNull(tx.getId());
        assertNotNull(tx.getEntryTime());
        assertTrue(tx.getSpot().isOccupied());

        // Step 3: Simulate exit
        tx.setExitTime(LocalDateTime.now().plusHours(2));
        transactionRepo.save(tx);

        // Step 4: Check-out
        ParkingTransaction completedTx = parkingService.checkOut(tx.getId());
        assertNotNull(completedTx.getExitTime());
        assertEquals(40.0, completedTx.getFee()); // 2 hours * 20/hr for CAR
        assertFalse(completedTx.getSpot().isOccupied());
    }
}
