package com.parkingApp.dto;

public class CarResponse {
    private Long id;
    private String licensePlate;
    private String arrivalTime;
    private String status;
    private Long attendantId;

    public CarResponse(Long id, String licensePlate, String arrivalTime, String status, Long attendantId) {
        this.id = id;
        this.licensePlate = licensePlate;
        this.arrivalTime = arrivalTime;
        this.status = status;
        this.attendantId = attendantId;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public String getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(String arrivalTime) { this.arrivalTime = arrivalTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getAttendantId() { return attendantId; }
    public void setAttendantId(Long attendantId) { this.attendantId = attendantId; }
}