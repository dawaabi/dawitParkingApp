package com.parkingApp.dto;

public class ParkingSlotDTO {
    private Long slotId;
    private String type;
    private String status;
    private CarDTO currentCar;

    public Long getSlotId() { return slotId; }
    public void setSlotId(Long slotId) { this.slotId = slotId; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public CarDTO getCurrentCar() { return currentCar; }
    public void setCurrentCar(CarDTO currentCar) { this.currentCar = currentCar; }
}
