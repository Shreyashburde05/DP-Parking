package com.smarthome.app.patterns.factory;

/**
 * Developed by Bob (Member 2) – Sedan Implementation
 */
public class Sedan extends Vehicle {
    public Sedan(String plate) { this.plateNumber = plate; this.baseFee = 5.0; }
    @Override public String getVehicleType() { return "Sedan"; }
}
