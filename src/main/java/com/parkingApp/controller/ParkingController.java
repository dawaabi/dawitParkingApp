package com.parkingApp.controller;

import com.parkingApp.dto.*;
import com.parkingApp.service.ParkingService;
import com.parkingApp.service.UserCredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
public class ParkingController {
    private final UserCredentialService userCredentialService;

    @Autowired
    public ParkingController(UserCredentialService userCredentialService) {
        this.userCredentialService = userCredentialService;
    }


    @Autowired
    private ParkingService parkingService;

//    @Autowired
//    private UserCredentialService userCredentialService;

    @GetMapping("/slots")
    public ResponseEntity<List<ParkingSlotDTO>> getAllSlots() {
        return new ResponseEntity<>(parkingService.getAllSlots(), HttpStatus.OK);
    }

    @GetMapping("/slots/available")
    public ResponseEntity<List<ParkingSlotDTO>> getAvailableSlots() {
        return new ResponseEntity<>(parkingService.getAvailableSlots(), HttpStatus.OK);
    }

    @GetMapping("/slots/{slotId}")
    public ResponseEntity<ParkingSlotDTO> getSlot(@PathVariable Long slotId) {
        return new ResponseEntity<>(parkingService.getSlot(slotId), HttpStatus.OK);
    }

    @PostMapping("/park")
    public ResponseEntity<String> parkCar(@RequestBody ParkRequestDTO request) {
        return new ResponseEntity<>(parkingService.parkCar(request), HttpStatus.OK);
    }

    @PostMapping("/unpark")
    public ResponseEntity<String> unparkCar(@RequestBody UnparkRequestDTO request) {
        return new ResponseEntity<>(parkingService.unparkCar(request), HttpStatus.OK);
    }

    @PostMapping("/attendants")
    public ResponseEntity<UserDTO> createAttendant(@RequestBody PostNewAttendant request) {
        return new ResponseEntity<>(userCredentialService.createAttendant(request), HttpStatus.CREATED);
    }

    @PostMapping("/attendants/login")
    public ResponseEntity<String> login(@RequestBody AuthorizationRequest request) {
        return new ResponseEntity<>(userCredentialService.login(request), HttpStatus.OK);
    }
}
