/**
 * STRATEGY PATTERN
 * Defines a family of algorithms for fee calculation.
 * Each vehicle type has its own pricing strategy.
 */
public interface FeeStrategy {
    double calculateFee(int hours);
}

class CarFee implements FeeStrategy {
    public double calculateFee(int hours) {
        return hours * 20.0;
    }
}

class BikeFee implements FeeStrategy {
    public double calculateFee(int hours) {
        return hours * 10.0;
    }
}

class TruckFee implements FeeStrategy {
    public double calculateFee(int hours) {
        return hours * 50.0;
    }
}
