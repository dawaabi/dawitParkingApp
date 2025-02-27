package com.parkingApp.repositories;

import com.parkingApp.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    List<Car> findByStatusAndAttendantId(String status, String attendantId);
}