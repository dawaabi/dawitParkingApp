package com.parkingApp.repositories;

import com.parkingApp.entities.ParkingAttendant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ParkingAttendantRepository extends JpaRepository<ParkingAttendant, String> {
    Optional<ParkingAttendant> findByUsername(String username);
}
