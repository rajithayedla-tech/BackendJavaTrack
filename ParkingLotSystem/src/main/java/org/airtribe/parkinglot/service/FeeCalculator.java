package org.airtribe.parkinglot.service;

import org.airtribe.parkinglot.entity.ParkingTransaction;
import org.springframework.stereotype.Component;
import java.time.Duration;

@Component
public class FeeCalculator {

    // Define hourly rates per vehicle type
    private static final double MOTORCYCLE_RATE = 10.0;
    private static final double CAR_RATE = 20.0;
    private static final double BUS_RATE = 50.0;

    /**
     * Calculates parking fee based on vehicle type and duration.
     */
    public double calculate(ParkingTransaction tx) {
        if (tx.getEntryTime() == null || tx.getExitTime() == null) {
            throw new IllegalArgumentException("Transaction must have entry and exit times");
        }

        long hours = Duration.between(tx.getEntryTime(), tx.getExitTime()).toHours();
        if (hours == 0) {
            hours = 1; // minimum 1 hour charge
        }

        switch (tx.getVehicle().getType()) {
            case MOTORCYCLE:
                return hours * MOTORCYCLE_RATE;
            case CAR:
                return hours * CAR_RATE;
            case BUS:
                return hours * BUS_RATE;
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + tx.getVehicle().getType());
        }
    }
}
