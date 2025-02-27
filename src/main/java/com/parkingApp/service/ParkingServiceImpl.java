package com.parkingApp.service;

import com.parkingApp.dto.*;
import com.parkingApp.entities.*;
import com.parkingApp.exception.ParkingSlotNotFoundException;
import com.parkingApp.repositories.ParkingAttendantRepository;
import com.parkingApp.repositories.ParkingSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    private ParkingSlotRepository slotRepository;

    @Autowired
    private ParkingAttendantRepository attendantRepository;

    private ParkingSlotDTO toDTO(ParkingSlot slot) {
        ParkingSlotDTO dto = new ParkingSlotDTO();
        dto.setSlotId(slot.getSlotId());
        dto.setType(slot.getType());
        dto.setStatus(slot.getStatus());
        if (slot.getCurrentCar() != null) {
            CarDTO carDTO = new CarDTO();
            carDTO.setLicensePlate(slot.getCurrentCar().getLicensePlate());
            carDTO.setColor(slot.getCurrentCar().getColor());
            carDTO.setMake(slot.getCurrentCar().getMake());
            carDTO.setModel(slot.getCurrentCar().getModel());
            dto.setCurrentCar(carDTO);
        }
        return dto;
    }

    private Car toEntity(CarDTO dto) {
        return new Car(dto.getLicensePlate(), dto.getColor(), dto.getMake(), dto.getModel());
    }

    @Override
    public List<ParkingSlotDTO> getAllSlots() {
        return slotRepository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public List<ParkingSlotDTO> getAvailableSlots() {
        return slotRepository.findAll().stream()
                .filter(slot -> "free".equals(slot.getStatus()))
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingSlotDTO getSlot(Long slotId) {
        ParkingSlot slot = slotRepository.findById(slotId)
                .orElseThrow(() -> new ParkingSlotNotFoundException("Slot with ID " + slotId + " not found"));
        return toDTO(slot);
    }

    @Override
    public String parkCar(ParkRequestDTO request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ParkingAttendant attendant = attendantRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Attendant not found"));

        for (ParkingSlot slot : slotRepository.findAll()) {
            if ("free".equals(slot.getStatus())) {
                Car car = toEntity(request.getCar());
                slot.setCurrentCar(car);
                slot.setStatus("occupied");
                slotRepository.save(slot);
                return "Attendant " + attendant.getUsername() + " parked car " + car.getLicensePlate() + " in slot " + slot.getSlotId();
            }
        }
        return "No available slots";
    }

    @Override
    public String unparkCar(UnparkRequestDTO request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        ParkingAttendant attendant = attendantRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Attendant not found"));

        ParkingSlot slot = slotRepository.findById(request.getSlotId())
                .orElseThrow(() -> new ParkingSlotNotFoundException("Slot with ID " + request.getSlotId() + " not found"));
        if ("occupied".equals(slot.getStatus())) {
            String licensePlate = slot.getCurrentCar().getLicensePlate();
            slot.setCurrentCar(null);
            slot.setStatus("free");
            slotRepository.save(slot);
            return "Attendant " + attendant.getUsername() + " unparked car " + licensePlate + " from slot " + slot.getSlotId();
        }
        return "Slot is not occupied";
    }
}
