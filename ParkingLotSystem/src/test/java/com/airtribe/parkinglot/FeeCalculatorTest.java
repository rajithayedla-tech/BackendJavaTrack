package com.airtribe.parkinglot;

import org.airtribe.parkinglot.entity.ParkingTransaction;
import org.airtribe.parkinglot.entity.Vehicle;
import org.airtribe.parkinglot.entity.VehicleType;
import org.airtribe.parkinglot.service.FeeCalculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class FeeCalculatorTest {

    private final FeeCalculator feeCalculator = new FeeCalculator();

    @Test
    void testMotorcycleFeeOneHour() {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(VehicleType.MOTORCYCLE);

        ParkingTransaction tx = new ParkingTransaction();
        tx.setVehicle(vehicle);
        tx.setEntryTime(LocalDateTime.now().minusMinutes(30));
        tx.setExitTime(LocalDateTime.now());

        double fee = feeCalculator.calculate(tx);
        assertEquals(10.0, fee);
    }

    @Test
    void testCarFeeTwoHours() {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(VehicleType.CAR);

        ParkingTransaction tx = new ParkingTransaction();
        tx.setVehicle(vehicle);
        tx.setEntryTime(LocalDateTime.now().minusHours(2));
        tx.setExitTime(LocalDateTime.now());

        double fee = feeCalculator.calculate(tx);
        assertEquals(40.0, fee);
    }

    @Test
    void testBusFeeThreeHours() {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(VehicleType.BUS);

        ParkingTransaction tx = new ParkingTransaction();
        tx.setVehicle(vehicle);
        tx.setEntryTime(LocalDateTime.now().minusHours(3));
        tx.setExitTime(LocalDateTime.now());

        double fee = feeCalculator.calculate(tx);
        assertEquals(150.0, fee);
    }

    @Test
    void testInvalidTransactionThrowsException() {
        Vehicle vehicle = new Vehicle();
        vehicle.setType(VehicleType.CAR);

        ParkingTransaction tx = new ParkingTransaction();
        tx.setVehicle(vehicle);
        tx.setEntryTime(null);
        tx.setExitTime(null);

        assertThrows(IllegalArgumentException.class, () -> feeCalculator.calculate(tx));
    }
}
