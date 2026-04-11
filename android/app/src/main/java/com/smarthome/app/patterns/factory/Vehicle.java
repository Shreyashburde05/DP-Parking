package com.smarthome.app.patterns.factory;

/**
 * Developed by Bob (Member 2) – Base Vehicle Class
 */
public abstract class Vehicle {
    protected String plateNumber;
    protected double baseFee;

    public abstract String getVehicleType();
    public double getFee() { return baseFee; }
    public String getPlate() { return plateNumber; }
}
