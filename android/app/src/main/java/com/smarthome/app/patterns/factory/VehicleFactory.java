package com.smarthome.app.patterns.factory;

/**
 * Developed by Bob (Member 2) – Vehicle Factory
 */
public class VehicleFactory {

    public static final String TYPE_SEDAN = "SEDAN";
    public static final String TYPE_CYCLE = "CYCLE";
    public static final String TYPE_TRUCK = "TRUCK";

    /**
     * Factory logic for Smart Parking Vehicles
     */
    public static Vehicle createVehicle(String type, String plate) {
        if (type == null) return null;

        switch (type.toUpperCase()) {
            case TYPE_SEDAN:
                return new Sedan(plate);
            case TYPE_CYCLE:
                return new TwoWheeler(plate);
            case TYPE_TRUCK:
                return new HeavyTruck(plate);
            default:
                return null;
        }
    }
}
