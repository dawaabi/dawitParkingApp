package com.parkingApp.service;

import com.parkingApp.dto.CarDTO;
import com.parkingApp.exception.CarNotFoundException;
import java.util.List;

public interface CarService {
    CarDTO saveCar(CarDTO carDTO);
    CarDTO getCarById(String id) throws CarNotFoundException;
    List<CarDTO> getAllCars();
    CarDTO updateCar(String id, CarDTO carDTO) throws CarNotFoundException;
    void deleteCar(String id) throws CarNotFoundException;
}