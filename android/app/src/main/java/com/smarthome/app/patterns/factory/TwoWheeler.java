package com.smarthome.app.patterns.factory;

/**
 * Developed by Bob (Member 2) – Cycle Implementation
 */
public class TwoWheeler extends Vehicle {
    public TwoWheeler(String plate) { this.plateNumber = plate; this.baseFee = 2.0; }
    @Override public String getVehicleType() { return "Two-Wheeler"; }
}
