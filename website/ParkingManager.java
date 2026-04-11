import java.util.*;

/**
 * SINGLETON PATTERN
 * Only one ParkingManager instance exists throughout the application.
 * Manages all parked vehicles and capacity.
 */
public class ParkingManager {
    private static ParkingManager instance;
    private final int capacity = 10;
    private final Map<String, Vehicle> parkedVehicles = new LinkedHashMap<>();

    private ParkingManager() {}

    public static synchronized ParkingManager getInstance() {
        if (instance == null) {
            instance = new ParkingManager();
        }
        return instance;
    }

    public synchronized String parkVehicle(String type, String plate) {
        if (parkedVehicles.containsKey(plate)) {
            return "{\"success\":false,\"message\":\"Vehicle " + plate + " is already parked.\"}";
        }
        if (parkedVehicles.size() >= capacity) {
            return "{\"success\":false,\"message\":\"Parking lot is full!\"}";
        }
        Vehicle v = VehicleFactory.createVehicle(type, plate);
        if (v == null) {
            return "{\"success\":false,\"message\":\"Unknown vehicle type: " + type + "\"}";
        }
        parkedVehicles.put(plate, v);
        return "{\"success\":true,\"message\":\"" + v.getType() + " [" + plate + "] parked successfully.\"}";
    }

    public synchronized String releaseVehicle(String plate, int hours) {
        Vehicle v = parkedVehicles.remove(plate);
        if (v == null) {
            return "{\"success\":false,\"message\":\"Vehicle not found.\"}";
        }
        double fee = v.getParkingFee(hours);
        return "{\"success\":true,\"message\":\"" + v.getType() + " [" + plate + "] released. Fee: Rs " + fee + "\",\"fee\":" + fee + "}";
    }

    public synchronized String getStatusJson() {
        StringBuilder sb = new StringBuilder();
        sb.append("{\"capacity\":").append(capacity);
        sb.append(",\"occupied\":").append(parkedVehicles.size());
        sb.append(",\"available\":").append(capacity - parkedVehicles.size());
        sb.append(",\"vehicles\":[");
        int i = 0;
        for (Vehicle v : parkedVehicles.values()) {
            if (i > 0) sb.append(",");
            long mins = (System.currentTimeMillis() - v.getParkedAtMillis()) / 60000;
            sb.append("{\"plate\":\"").append(v.getLicensePlate()).append("\"");
            sb.append(",\"type\":\"").append(v.getType()).append("\"");
            sb.append(",\"minutes\":").append(mins).append("}");
            i++;
        }
        sb.append("]}");
        return sb.toString();
    }
}