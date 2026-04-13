package com.smarthome.app.patterns.decorator;

import com.smarthome.app.patterns.factory.Vehicle;

/**
 * Concrete Decorator: Adds Car Wash service to a vehicle.
 */
public class CarWashDecorator extends VehicleDecorator {
    public CarWashDecorator(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public String getVehicleType() {
        return decoratedVehicle.getVehicleType() + " + Car Wash";
    }

    @Override
    public double getFee() {
        return decoratedVehicle.getFee() + 10.0; // Adding $10 for car wash
    }
}
