package com.smarthome.app.patterns.decorator;

import com.smarthome.app.patterns.factory.Vehicle;

/**
 * Structural Pattern: Decorator
 * 
 * Base decorator class that implements the same interface (or abstract class) 
 * as the objects it will decorate.
 */
public abstract class VehicleDecorator extends Vehicle {
    protected Vehicle decoratedVehicle;

    public VehicleDecorator(Vehicle vehicle) {
        this.decoratedVehicle = vehicle;
        this.plateNumber = vehicle.getPlate();
    }

    @Override
    public String getPlate() {
        return decoratedVehicle.getPlate();
    }
}
