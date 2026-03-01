package org.airtribe.parkinglot.repository;

import org.airtribe.parkinglot.entity.ParkingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingTransactionRepository extends JpaRepository<ParkingTransaction, Long> {
    List<ParkingTransaction> findByVehicle_Id(Long vehicleId);
}
