package org.airtribe.parkinglot.service;

import org.airtribe.parkinglot.entity.ParkingSpot;
import org.airtribe.parkinglot.entity.ParkingTransaction;
import org.airtribe.parkinglot.entity.Vehicle;
import org.airtribe.parkinglot.repository.ParkingSpotRepository;
import org.airtribe.parkinglot.repository.ParkingTransactionRepository;
import org.airtribe.parkinglot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ParkingService {

    @Autowired private SpotAllocator spotAllocator;
    @Autowired private ParkingTransactionRepository transactionRepo;
    @Autowired private VehicleRepository vehicleRepo;
    @Autowired private ParkingSpotRepository spotRepo;
    @Autowired private FeeCalculator feeCalculator;

    /**
     * Handles vehicle check-in:
     * - Saves vehicle
     * - Allocates spot
     * - Creates transaction
     */
    @Transactional
    public ParkingTransaction checkIn(Vehicle vehicle) {
        vehicleRepo.save(vehicle);

        ParkingSpot spot = spotAllocator.allocateSpot(vehicle.getType());
        spot.setOccupied(true);
        spotRepo.save(spot);

        ParkingTransaction tx = new ParkingTransaction();
        tx.setVehicle(vehicle);
        tx.setSpot(spot);
        tx.setEntryTime(LocalDateTime.now());

        return transactionRepo.save(tx);
    }

    /**
     * Handles vehicle check-out:
     * - Updates transaction with exit time
     * - Calculates fee
     * - Frees spot
     */
    @Transactional
    public ParkingTransaction checkOut(Long transactionId) {
        ParkingTransaction tx = transactionRepo.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));

        tx.setExitTime(LocalDateTime.now());
        double fee = feeCalculator.calculate(tx);
        tx.setFee(fee);

        ParkingSpot spot = tx.getSpot();
        spot.setOccupied(false);
        spotRepo.save(spot);

        return transactionRepo.save(tx);
    }
}
