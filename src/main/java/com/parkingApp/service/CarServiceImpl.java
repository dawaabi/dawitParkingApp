package com.parkingApp.service;

import com.parkingApp.dto.CarDTO;
import com.parkingApp.entities.Car;
import com.parkingApp.exception.CarNotFoundException;
import com.parkingApp.repositories.CarRepository;
import com.parkingApp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDTO saveCar(CarDTO carDTO) {
        Car car = toEntity(carDTO);
        Car savedCar = carRepository.save(car);
        return toDTO(savedCar);
    }

    @Override
    public CarDTO getCarById(String id) throws CarNotFoundException {
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + id));
        return toDTO(car);
    }

    @Override
    public List<CarDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public CarDTO updateCar(String id, CarDTO carDTO) throws CarNotFoundException {
        Car existingCar = carRepository.findById(id)
                .orElseThrow(() -> new CarNotFoundException("Car not found with id: " + id));
        existingCar.setColor(carDTO.getColor());
        existingCar.setMake(carDTO.getMake());
        existingCar.setModel(carDTO.getModel());
        Car updatedCar = carRepository.save(existingCar);
        return toDTO(updatedCar);
    }

    @Override
    public void deleteCar(String id) throws CarNotFoundException {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException("Car not found with id: " + id);
        }
        carRepository.deleteById(id);
    }

    private CarDTO toDTO(Car car) {
        CarDTO dto = new CarDTO();
        dto.setLicensePlate(car.getLicensePlate());
        dto.setColor(car.getColor());
        dto.setMake(car.getMake());
        dto.setModel(car.getModel());
        return dto;
    }

    private Car toEntity(CarDTO dto) {
        Car car = new Car();
        car.setLicensePlate(dto.getLicensePlate());
        car.setColor(dto.getColor());
        car.setMake(dto.getMake());
        car.setModel(dto.getModel());
        return car;
    }
}