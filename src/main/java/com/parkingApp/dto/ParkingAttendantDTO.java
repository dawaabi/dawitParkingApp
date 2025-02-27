package com.parkingApp.dto;

public class ParkingAttendantDTO {
    private String username;
    private String role;

    public ParkingAttendantDTO(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() { return username; }
    public String getRole() { return role; }
}
