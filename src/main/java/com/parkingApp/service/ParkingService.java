package com.parkingApp.service;

import com.parkingApp.dto.*;

import java.util.List;

public interface ParkingService {
    List<ParkingSlotDTO> getAllSlots();
    List<ParkingSlotDTO> getAvailableSlots();
    ParkingSlotDTO getSlot(Long slotId);
    String parkCar(ParkRequestDTO request);
    String unparkCar(UnparkRequestDTO request);
}