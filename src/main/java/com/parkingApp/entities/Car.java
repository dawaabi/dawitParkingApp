package com.parkingApp.entities;

import com.parkingApp.enums.CarStatus;
import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Car {
    @Id
    private String licensePlate;
    private String color;
    private String make;
    private String model;
    private Date arrivalTime;
    private Date departureTime;

    @Enumerated(EnumType.STRING)  // Stores the enum as a string in the database
    private CarStatus status;

    @ManyToOne
    @JoinColumn(name = "attendant_id")
    private ParkingAttendant attendant;

    // Constructors
    public Car() {}

    public Car(String licensePlate, String color, String make, String model, ParkingAttendant attendant, CarStatus status) {
        this.licensePlate = licensePlate;
        this.color = color;
        this.make = make;
        this.model = model;
        this.attendant = attendant;
        this.status = status;
    }

    public Car(String licensePlate, String color, String make, String model) {
        this.licensePlate=licensePlate;
        this.color=color;
        this.make=make;
        this.model=model;
    }

    // Getters and Setters
    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    public String getMake() { return make; }
    public void setMake(String make) { this.make = make; }
    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }
    public Date getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(Date arrivalTime) { this.arrivalTime = arrivalTime; }
    public Date getDepartureTime() { return departureTime; }
    public void setDepartureTime(Date departureTime) { this.departureTime = departureTime; }
    public CarStatus getStatus() { return status; }
    public void setStatus(CarStatus status) { this.status = status; }
    public ParkingAttendant getAttendant() { return attendant; }
    public void setAttendant(ParkingAttendant attendant) { this.attendant = attendant; }
}