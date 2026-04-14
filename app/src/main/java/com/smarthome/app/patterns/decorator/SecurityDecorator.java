package com.smarthome.app.patterns.decorator;

import com.smarthome.app.patterns.factory.Vehicle;

/**
 * Concrete Decorator: Adds Premium Security service to a vehicle.
 */
public class SecurityDecorator extends VehicleDecorator {
    public SecurityDecorator(Vehicle vehicle) {
        super(vehicle);
    }

    @Override
    public String getVehicleType() {
        return decoratedVehicle.getVehicleType() + " + Premium Security";
    }

    @Override
    public double getFee() {
        return decoratedVehicle.getFee() + 15.0; // Adding $15 for security
    }
}
