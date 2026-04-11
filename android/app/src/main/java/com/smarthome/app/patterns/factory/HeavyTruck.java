package com.smarthome.app.patterns.factory;

/**
 * Developed by Bob (Member 2) – Truck Implementation
 */
public class HeavyTruck extends Vehicle {
    public HeavyTruck(String plate) { this.plateNumber = plate; this.baseFee = 15.0; }
    @Override public String getVehicleType() { return "Heavy Truck"; }
}
