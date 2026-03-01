package org.airtribe.parkinglot.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String licensePlate;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private LocalDateTime entryTime;
    private LocalDateTime exitTime;

    public Vehicle() {
    }

    public Vehicle(Long id, String licensePlate, VehicleType type) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(LocalDateTime entryTime) {
        this.entryTime = entryTime;
    }

    public LocalDateTime getExitTime() {
        return exitTime;
    }

    public void setExitTime(LocalDateTime exitTime) {
        this.exitTime = exitTime;
    }
}
