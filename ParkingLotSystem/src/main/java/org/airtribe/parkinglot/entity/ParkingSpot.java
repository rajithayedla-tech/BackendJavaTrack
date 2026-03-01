package org.airtribe.parkinglot.entity;

import jakarta.persistence.*;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int floor;
    private int spotNumber;

    @Enumerated(EnumType.STRING)
    private VehicleType size;

    private boolean isOccupied;

    public ParkingSpot() {
    }

    public ParkingSpot(Long id, int floor, int spotNumber, VehicleType size, boolean isOccupied) {
        this.id = id;
        this.floor = floor;
        this.spotNumber = spotNumber;
        this.size = size;
        this.isOccupied = isOccupied;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getSpotNumber() {
        return spotNumber;
    }

    public void setSpotNumber(int spotNumber) {
        this.spotNumber = spotNumber;
    }

    public VehicleType getSize() {
        return size;
    }

    public void setSize(VehicleType size) {
        this.size = size;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}

