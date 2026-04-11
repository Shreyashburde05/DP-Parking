/**
 * Base class for all vehicles.
 * Each vehicle holds a FeeStrategy (Strategy pattern) to calculate its parking fee.
 */
public abstract class Vehicle {
    protected String licensePlate;
    protected FeeStrategy feeStrategy;
    protected long parkedAtMillis;

    public Vehicle(String licensePlate, FeeStrategy feeStrategy) {
        this.licensePlate = licensePlate;
        this.feeStrategy = feeStrategy;
        this.parkedAtMillis = System.currentTimeMillis();
    }

    public String getLicensePlate() { return licensePlate; }
    public long getParkedAtMillis() { return parkedAtMillis; }

    public double getParkingFee(int hours) {
        return feeStrategy.calculateFee(hours);
    }

    public abstract String getType();
}

class Car extends Vehicle {
    public Car(String plate) { super(plate, new CarFee()); }
    public String getType() { return "Car"; }
}

class Bike extends Vehicle {
    public Bike(String plate) { super(plate, new BikeFee()); }
    public String getType() { return "Bike"; }
}

class Truck extends Vehicle {
    public Truck(String plate) { super(plate, new TruckFee()); }
    public String getType() { return "Truck"; }
}