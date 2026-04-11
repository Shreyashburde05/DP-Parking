/**
 * FACTORY PATTERN
 * Creates Vehicle objects based on a type string.
 * The caller doesn't need to know the concrete classes.
 */
public class VehicleFactory {
    public static Vehicle createVehicle(String type, String licensePlate) {
        if (type == null) return null;

        switch (type.toLowerCase()) {
            case "car":   return new Car(licensePlate);
            case "bike":  return new Bike(licensePlate);
            case "truck": return new Truck(licensePlate);
            default:      return null;
        }
    }
}
