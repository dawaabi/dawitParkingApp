package com.parkingApp.entities;

import jakarta.persistence.*;

@Entity
public class ParkingSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long slotId;
    private String type;
    private String status;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "current_car")
    private Car currentCar;

    public ParkingSlot() {}
    public ParkingSlot(String type, String status, Car currentCar) {
        this.type = type;
        this.status = status;
        this.currentCar = currentCar;
    }

    // Getters and Setters
    public Long getSlotId() { return slotId; }
    public void setSlotId(Long slotId) { this.slotId = slotId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Car getCurrentCar() { return currentCar; }
    public void setCurrentCar(Car currentCar) { this.currentCar = currentCar; }
}
