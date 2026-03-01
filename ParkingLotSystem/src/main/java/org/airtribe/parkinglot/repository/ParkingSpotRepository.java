package org.airtribe.parkinglot.repository;

import org.airtribe.parkinglot.entity.ParkingSpot;
import org.airtribe.parkinglot.entity.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {
    Optional<ParkingSpot> findFirstBySizeAndIsOccupiedFalseOrderByFloorAscSpotNumberAsc(VehicleType type);
}



