package com.parkingApp.dto;



import java.time.LocalDateTime;

public class CarRequest {
    private String licensePlate;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private String status;
    private Long attendantId;


    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(LocalDateTime arrivalTime) { this.arrivalTime = arrivalTime; }
    public LocalDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(LocalDateTime departureTime) { this.departureTime = departureTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Long getAttendantId() { return attendantId; }
    public void setAttendantId(Long attendantId) { this.attendantId = attendantId; }
}
